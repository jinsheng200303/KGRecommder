package cn.hunnu.recommender.course.controller;

import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassesQuery;
import cn.hunnu.recommender.course.entity.ClassAnnouncement;
import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.service.ClassesService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 课堂 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@RestController
@RequestMapping("/classes")
@Api(value = "课堂模块",tags = "课堂模块")
public class ClassesController extends CourseBaseController {
    @ApiOperation(value = "课堂列表",notes = "课堂列表")
    @GetMapping("/list")
    public List<Classes> list() {
        return classesService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂新增/修改",notes = "课堂新增/修改")
    public Result save(@Validated @RequestBody Classes classes){
        classesService.saveOrUpdate(classes);
        return Result.success();
    }

    //根据ID删除课堂
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂",notes = "删除课堂")
    public Result delete(@RequestBody List<Integer> IDS){
        classesService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂查询",notes = "课堂查询")
    public Result<Page<Classes>> queryClasses(@RequestBody ClassesQuery classesQuery){


        LambdaQueryWrapper<Classes> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Classes::getClassId);

        if(!"".equals(classesQuery.getClassName())&& classesQuery.getClassName()!=null){
            wrapper.like(Classes::getClassName, classesQuery.getClassName());
        }

        Page<Classes> page = classesService.page(
                new Page<>(
                        classesQuery.getPageNum(),
                        classesQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
