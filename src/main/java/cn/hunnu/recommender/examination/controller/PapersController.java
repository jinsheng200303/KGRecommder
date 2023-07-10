package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.GeneratePapersQuery;
import cn.hunnu.recommender.examination.dto.PapersQuery;
import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.examination.entity.Papers;
import cn.hunnu.recommender.examination.entity.PapersQuestions;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.service.PapersQuestionsService;
import cn.hunnu.recommender.examination.service.QuestionsService;
import cn.hunnu.recommender.exception.CustomException;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 试卷表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "试卷模块", tags = "试卷模块")
@RestController
@RequestMapping("/papers")
public class PapersController extends ExaminationBaseController {

    @Resource
    private QuestionsService questionsService;

    @Resource
    PapersQuestionsService papersQuestionsService;

    @ApiOperation(value = "试卷列表", notes = "试卷列表")
    @GetMapping("/list")
    public List<Papers> list() {
        return papersService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<Papers>> findPage(@RequestBody PapersQuery papersQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Papers> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Papers::getExamId);

        if (!"".equals(papersQuery.getPaperTitle()) && papersQuery.getPaperTitle() != null) {
            wrapper.like(Papers::getPaperTitle, papersQuery.getPaperTitle());
        }

        Page<Papers> page = papersService.page(
                new Page<>(
                        papersQuery.getPageNum(),
                        papersQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新", notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Papers papers) {

//        throw new CustomException("这个是自定义异常");

        papersService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除", notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        papersService.removeByIds(ids);
        return Result.success();
    }


    @ApiOperation(value = "自动生成试卷", notes = "自动生成试卷")
    @PostMapping("/autoTakePaper")
    public Result autoTakePaper(@RequestBody GeneratePapersQuery generatePapersQuery) {
        //删除之前生成的重复ID的试卷
        UpdateWrapper<PapersQuestions> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("paper_id", generatePapersQuery.getPaperId());
        papersQuestionsService.remove(updateWrapper);

        QueryWrapper<Questions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bank_id", generatePapersQuery.getBankId());
        //根据题库id查出所有该题库的题目，然后根据type区分
        List<Questions> questionsList = questionsService.list(queryWrapper);

        List<Questions> typeChoiceList = questionsList.stream()
                .filter(questions -> questions.getQuestionType().equals("1"))
                .collect(Collectors.toList());//选择
        List<Questions> typeGapList = questionsList.stream()
                .filter(questions -> questions.getQuestionType().equals("2"))
                .collect(Collectors.toList());//填空
        List<Questions> typeEssayList = questionsList.stream()
                .filter(questions -> questions.getQuestionType().equals("3"))
                .collect(Collectors.toList());//问答

        if (typeChoiceList.size() < generatePapersQuery.getTypeChoice()) {
            throw new CustomException(-1, "选择题数量不足");
        }
        if (typeGapList.size() < generatePapersQuery.getTypeGap()) {
            throw new CustomException(-1, "填空题数量不足");
        }
        if (typeEssayList.size() < generatePapersQuery.getTypeEssay()) {
            throw new CustomException(-1, "问答题数量不足");
        }
        //开始随机组卷
        List<PapersQuestions> papersQuestions = getPapersQuestions(typeChoiceList.size(), generatePapersQuery.getTypeChoice(), typeChoiceList, generatePapersQuery.getPaperId());
        papersQuestions.addAll(getPapersQuestions(typeGapList.size(), generatePapersQuery.getTypeGap(), typeGapList, generatePapersQuery.getPaperId()));
        papersQuestions.addAll(getPapersQuestions(typeEssayList.size(), generatePapersQuery.getTypeEssay(), typeEssayList, generatePapersQuery.getPaperId()));
        papersQuestionsService.saveBatch(papersQuestions); //批量插入关联关系表
        return Result.success();
    }

    //封装一个获取试卷和题目关联关系list的方法
    private List<PapersQuestions> getPapersQuestions(int questionSize, int paperQuestionSize, List<Questions> source, Integer paperId) {
        List<Integer> typeRandomList = getEleList(questionSize, paperQuestionSize);
        List<PapersQuestions> list = new ArrayList<>();
        for (Integer index : typeRandomList) {
            Questions questions = source.get(index);
            PapersQuestions papersQuestions = new PapersQuestions();
            papersQuestions.setPaperId(paperId);
            papersQuestions.setQuestionId(questions.getQuestionId());
            list.add(papersQuestions);
        }
        return list;
    }

    //封装一个获取不重复随机数的方法
    private List<Integer> getEleList(int sourceSize, int resultSize) {
        List<Integer> list = CollUtil.newArrayList();
        for (int i = 0; i < sourceSize; i++) {
            list.add(i);
        }
        return RandomUtil.randomEleList(list, resultSize);
    }

    @ApiOperation(value = "根据试卷ID查找其包含的试题信息", notes = "根据试卷ID查找其包含的试题信息")
    @GetMapping("/view/{paperId}")
    public Result view(@PathVariable Integer paperId){
        List<Questions> list = papersQuestionsService.selectQuestions(paperId);
        return Result.success(list);
    }

}
