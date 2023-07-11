package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.StudentPaperQuery;
import cn.hunnu.recommender.examination.entity.StudentPaper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.hunnu.recommender.examination.controller.ExaminationBaseController;

import java.util.List;

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
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody StudentPaper studentPaper) {

//        throw new CustomException("这个是自定义异常");

        studentPaperService.saveOrUpdate(studentPaper);
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
