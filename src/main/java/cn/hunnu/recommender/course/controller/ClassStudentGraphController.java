package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassStudentGraphQuery;
import cn.hunnu.recommender.course.entity.ClassStudentGraph;
import cn.hunnu.recommender.course.service.ClassStudentGraphService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课堂学生图谱 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/class-student-graph")
@Api(value = "课堂学生图谱关联模块",tags = "课堂学生图谱关联模块")
public class ClassStudentGraphController extends CourseBaseController {
    @ApiOperation(value = "课堂学生图谱关联列表",notes = "课堂学生图谱关联列表")
    @GetMapping("/list")
    public List<ClassStudentGraph> list() {
        return classStudentGraphService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂学生图谱关联的新增/修改",notes = "课堂学生图谱关联的新增/修改")
    public Result save(@Validated @RequestBody ClassStudentGraph classStudentGraph){
        classStudentGraphService.saveOrUpdate(classStudentGraph);
        return Result.success();
    }

    //根据ID删除课堂学生图谱关联信息
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂学生图谱关联信息",notes = "删除课堂学生图谱关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        classStudentGraphService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂学生图谱关联查询",notes = "课堂学生图谱关联查询")
    public Result queryClassStudentGraph(@RequestBody ClassStudentGraphQuery classStudentGraphQuery){


        LambdaQueryWrapper<ClassStudentGraph> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ClassStudentGraph::getClassId);

        if(!"".equals(classStudentGraphQuery.getGraphID())&& classStudentGraphQuery.getGraphID()!=null){
            wrapper.like(ClassStudentGraph::getGraphId, classStudentGraphQuery.getGraphID());
        }

        if(!"".equals(classStudentGraphQuery.getClassID())&& classStudentGraphQuery.getClassID()!=null){
            wrapper.like(ClassStudentGraph::getClassId, classStudentGraphQuery.getClassID());
        }

        if(!"".equals(classStudentGraphQuery.getUserID())&& classStudentGraphQuery.getUserID()!=null){
            wrapper.like(ClassStudentGraph::getUserId, classStudentGraphQuery.getUserID());
        }

        Page<ClassStudentGraph> page = classStudentGraphService.page(
                new Page<>(
                        classStudentGraphQuery.getPageNum(),
                        classStudentGraphQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
