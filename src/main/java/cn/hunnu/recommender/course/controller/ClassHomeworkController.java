package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassHomeworkQuery;
import cn.hunnu.recommender.course.entity.ClassHomework;
import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.service.ClassHomeworkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 课堂作业 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/class-homework")
@Api(value = "课堂作业关联模块",tags = "课堂作业关联模块")
public class ClassHomeworkController extends CourseBaseController {
    @ApiOperation(value = "课堂作业关联列表",notes = "课堂作业关联列表")
    @GetMapping("/list")
    public List<ClassHomework> list() {
        return classHomeworkService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂作业关联的新增/修改",notes = "课堂作业关联的新增/修改")
    public Result save(@Validated @RequestBody ClassHomework classHomework){
        classHomeworkService.saveOrUpdate(classHomework);
        return Result.success();
    }

    //根据ID删除课堂作业关联信息
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂作业关联信息",notes = "删除课堂作业关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        classHomeworkService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂作业关联查询",notes = "课堂作业关联查询")
    public Result<Page<ClassHomework>> queryClassHomework(@RequestBody ClassHomeworkQuery classHomeworkQuery){


        LambdaQueryWrapper<ClassHomework> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ClassHomework::getClassId);

        if(!"".equals(classHomeworkQuery.getHomeworkID())&& classHomeworkQuery.getHomeworkID()!=null){
            wrapper.like(ClassHomework::getHomeworkId, classHomeworkQuery.getHomeworkID());
        }

        if(!"".equals(classHomeworkQuery.getClassID())&& classHomeworkQuery.getClassID()!=null){
            wrapper.like(ClassHomework::getClassId, classHomeworkQuery.getClassID());
        }

        Page<ClassHomework> page = classHomeworkService.page(
                new Page<>(
                        classHomeworkQuery.getPageNum(),
                        classHomeworkQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
