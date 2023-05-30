package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.ExamsQuery;
import cn.hunnu.recommender.examination.entity.Exams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 考试表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-19
 */
@Api(value = "考试模块",tags = "考试模块")

@RestController
@RequestMapping("/exams")
public class ExamsController extends ExaminationBaseController {

    @ApiOperation(value = "考试列表",notes = "考试列表")
    @GetMapping("/list")
    public List<Exams> list() {
        return examsService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result findPage(@RequestBody ExamsQuery examsQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Exams::getExamId);

        if (!"".equals(examsQuery.getExamTitle()) && examsQuery.getExamTitle() != null) {
            wrapper.like(Exams::getExamTitle,examsQuery.getExamTitle());
        }

        Page<Exams> page = examsService.page(
                new Page<>(
                        examsQuery.getPageNum(),
                        examsQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Exams exams) {

//        throw new CustomException("这个是自定义异常");

        examsService.saveOrUpdate(exams);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        examsService.removeByIds(ids);
        return Result.success();
    }

}
