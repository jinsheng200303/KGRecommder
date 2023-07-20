package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.StudentPaperQuery;
import cn.hunnu.recommender.examination.entity.QuestionKnowledge;
import cn.hunnu.recommender.examination.entity.StudentPaper;
import cn.hunnu.recommender.examination.mapper.QuestionKnowledgeMapper;
import cn.hunnu.recommender.examination.mapper.StudentPaperMapper;
import cn.hunnu.recommender.exception.CustomException;
import cn.hunnu.recommender.user.utils.JwtUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import cn.hunnu.recommender.examination.controller.ExaminationBaseController;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-11
 */
@Api(value = "学生考试阅卷模块",tags = "学生考试阅卷模块")
@RestController
@RequestMapping("/student-paper")
public class StudentPaperController extends ExaminationBaseController {

    @Autowired
    StudentPaperMapper studentPaperMapper;

    @Autowired
    QuestionKnowledgeMapper questionKnowledgeMapper;
    @ApiOperation(value = "学生考试记录列表",notes = "学生考试记录列表")
    @GetMapping("/list")
    public List<StudentPaper> list() {
        return studentPaperService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<StudentPaper>> findPage(@RequestBody StudentPaperQuery studentPaperQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<StudentPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(StudentPaper::getStudentPaperId);

        if (!"".equals(studentPaperQuery.getExamId()) && studentPaperQuery.getExamId() != null) {
            wrapper.eq(StudentPaper::getExamId, studentPaperQuery.getExamId());
        }

        if (!"".equals(studentPaperQuery.getUserId()) && studentPaperQuery.getUserId() != null) {
            wrapper.eq(StudentPaper::getUserId, studentPaperQuery.getUserId());
        }

        Page<StudentPaper> page = studentPaperService.page(
                new Page<>(
                        studentPaperQuery.getPageNum(),
                        studentPaperQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "学生提交考卷保存数据",notes = "学生提交考卷保存数据")
    @PostMapping("/save")
    public Result save(@RequestBody StudentPaper studentPaper){
        //设置不可重复提交
        if (studentPaper.getStudentPaperId() == null){
            List<StudentPaper> list = studentPaperService.list(new QueryWrapper<StudentPaper>().eq("exam_id", studentPaper.getExamId())
                    .eq("user_id", studentPaper.getUserId()));
            if (CollUtil.isNotEmpty(list)){
                throw new CustomException(-3, "您已经提交考卷！");
            }
            studentPaper.setTime(DateUtil.now());
            studentPaper.setUserId(studentPaper.getUserId());
        }

//        studentPaperService.saveOrUpdate(studentPaper);

        JSONArray json = JSON.parseArray(studentPaper.getPaper()); // 首先把字符串转成 JSONArray  对象
        int questionKnowledgeMatrix[][] = new int[json.size()][188]; //试题知识点矩阵
        int questionScoreMatrix[] = new int[json.size()];   //试题得分矩阵，0没分，1有分
        int studentKnowledgeMatrix[][] = new int[json.size()][3]; //学生知识点掌握程度矩阵
        //填充试题知识点矩阵和学生得分矩阵
        if(json.size()>0){
            int j = 0;
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                QuestionKnowledge questionKnowledge = questionKnowledgeMapper.findRecord((Integer) job.get("id"));
                questionKnowledgeMatrix[i][questionKnowledge.getKnowledgeId()] = 1;
                studentKnowledgeMatrix[i][0] = studentPaper.getUserId();
                studentKnowledgeMatrix[i][1] = questionKnowledge.getKnowledgeId();
                if(job.get("answer").equals(job.get("studentAnswer"))){
                    questionScoreMatrix[i] = 1;
                }else {
                    questionScoreMatrix[i] = 0;
                }
            }
        }

        try {
            // 定义Python接口的URL
            String pythonApiUrl = "http://localhost:5000/dina";
            // 创建URL对象
            URL url = new URL(pythonApiUrl);
            // 创建HTTP连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // 发送POST请求
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("q", new JSONArray(Collections.singletonList(questionKnowledgeMatrix)));
            jsonRequest.put("data", new JSONArray(Collections.singletonList(questionScoreMatrix)));
            conn.setDoOutput(true);
            // 获取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // 输出响应结果
            System.out.println("Python接口的响应：" + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try{
//            // 创建ObjectMapper对象
//            ObjectMapper objectMapper = new ObjectMapper();
//            // 解析JSON数据
//            JsonNode rootNode = objectMapper.readTree(studentPaper.getPaper());
//            // 提取中间的一维数组
//            JsonNode arrayNode = rootNode.get(0);
//            for (int j = 0; j < json.size(); j++){
//                float comprehension = Float.parseFloat(arrayNode.get(j).toString());
////                studentPaperMapper.deletePersonKnowledge(studentKnowledgeMatrix[j][0],
////                        studentKnowledgeMatrix[j][1],comprehension);
////                studentPaperMapper.savePersonKnowledge(studentKnowledgeMatrix[j][0],
////                        studentKnowledgeMatrix[j][1],comprehension);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        studentPaperService.removeByIds(ids);
        return Result.success();
    }

}
