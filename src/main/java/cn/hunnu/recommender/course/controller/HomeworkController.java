package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.HomeworkQuery;
import cn.hunnu.recommender.course.entity.Homework;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.hunnu.recommender.course.controller.CourseBaseController;

import java.util.List;

/**
 * <p>
 * 作业 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "作业模块",tags = "作业模块")
@RestController
@RequestMapping("/homework")
public class HomeworkController extends CourseBaseController {

    @ApiOperation(value = "作业列表",notes = "作业列表")
    @GetMapping("/list")
    public List<Homework> list() {
        return homeworkService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result findPage(@RequestBody HomeworkQuery homeworkQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Homework> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Homework::getHomeworkId);

        if (!"".equals(homeworkQuery.getHomeworkTitle()) && homeworkQuery.getHomeworkTitle() != null) {
            wrapper.like(Homework::getTitle, homeworkQuery.getHomeworkTitle());
        }

        Page<Homework> page = homeworkService.page(
                new Page<>(
                        homeworkQuery.getPageNum(),
                        homeworkQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Homework papers) {

//        throw new CustomException("这个是自定义异常");

        homeworkService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        homeworkService.removeByIds(ids);
        return Result.success();
    }
}
