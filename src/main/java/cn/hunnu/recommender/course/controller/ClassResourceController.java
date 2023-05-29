package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassResourceQuery;
import cn.hunnu.recommender.course.entity.ClassResource;
import cn.hunnu.recommender.course.service.ClassResourceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 课堂资源 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/class-resource")
@Api(value = "课堂资源关联模块",tags = "课堂资源关联模块")
public class ClassResourceController extends CourseBaseController {
    @ApiOperation(value = "课堂资源关联列表",notes = "课堂资源关联列表")
    @GetMapping("/list")
    public List<ClassResource> list() {
        return classResourceService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂资源关联的新增/修改",notes = "课堂资源关联的新增/修改")
    public Result save(@Validated @RequestBody ClassResource classResource){
        classResourceService.saveOrUpdate(classResource);
        return Result.success();
    }

    //根据ID删除课堂资源关联信息
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂资源关联信息",notes = "删除课堂资源关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        classResourceService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂资源关联查询",notes = "课堂资源关联查询")
    public Result queryClassResource(@RequestBody ClassResourceQuery classResourceQuery){


        LambdaQueryWrapper<ClassResource> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ClassResource::getClassId);

        if(!"".equals(classResourceQuery.getMaterialID())&& classResourceQuery.getMaterialID()!=null){
            wrapper.like(ClassResource::getMaterialId, classResourceQuery.getMaterialID());
        }

        if(!"".equals(classResourceQuery.getClassID())&& classResourceQuery.getClassID()!=null){
            wrapper.like(ClassResource::getClassId, classResourceQuery.getClassID());
        }

        Page<ClassResource> page = classResourceService.page(
                new Page<>(
                        classResourceQuery.getPageNum(),
                        classResourceQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
