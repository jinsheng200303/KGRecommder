package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassCategoryQuery;
import cn.hunnu.recommender.course.entity.ClassCategory;
import cn.hunnu.recommender.course.service.ClassCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 课堂类别 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/class-category")
@Api(value = "课堂类别关联模块",tags = "课堂类别关联模块")
public class ClassCategoryController extends CourseBaseController {
    @ApiOperation(value = "课堂类别关联列表",notes = "课堂类别关联列表")
    @GetMapping("/list")
    public List<ClassCategory> list() {
        return classCategoryService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂类别关联的新增/修改",notes = "课堂类别关联的新增/修改")
    public Result save(@Validated @RequestBody ClassCategory classCategory){
        classCategoryService.saveOrUpdate(classCategory);
        return Result.success();
    }

    //根据ID删除课堂类别关联信息
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂类别关联信息",notes = "删除课堂类别关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        classCategoryService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂类别关联查询",notes = "课堂类别关联查询")
    public Result<Page<ClassCategory>> queryClassCategory(@RequestBody ClassCategoryQuery classCategoryQuery){


        LambdaQueryWrapper<ClassCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ClassCategory::getClassId);

        if(!"".equals(classCategoryQuery.getCategoryID())&& classCategoryQuery.getCategoryID()!=null){
            wrapper.like(ClassCategory::getCategoryId, classCategoryQuery.getCategoryID());
        }

        if(!"".equals(classCategoryQuery.getClassID())&& classCategoryQuery.getClassID()!=null){
            wrapper.like(ClassCategory::getClassId, classCategoryQuery.getClassID());
        }

        Page<ClassCategory> page = classCategoryService.page(
                new Page<>(
                        classCategoryQuery.getPageNum(),
                        classCategoryQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
