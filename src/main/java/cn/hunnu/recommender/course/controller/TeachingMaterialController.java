package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.TeachingMaterialQuery;
import cn.hunnu.recommender.course.entity.TeachingMaterial;
import cn.hunnu.recommender.course.service.TeachingMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 教学资料 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-30
 */
@RestController
@RequestMapping("/teaching-material")
@Api(value = "教学资料模块",tags = "教学资料模块")
public class TeachingMaterialController extends CourseBaseController {
    @ApiOperation(value = "教学资料列表",notes = "教学资料列表")
    @GetMapping("/list")
    public List<TeachingMaterial> list() {
        return teachingMaterialService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "教学资料新增/修改",notes = "教学资料新增/修改")
    public Result save(@Validated @RequestBody TeachingMaterial teachingMaterial){
        teachingMaterialService.saveOrUpdate(teachingMaterial);
        return Result.success();
    }

    //根据ID删除教学资料
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除教学资料",notes = "删除教学资料")
    public Result delete(@RequestBody List<Integer> IDS){
        teachingMaterialService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "教学资料查询",notes = "教学资料查询")
    public Result queryTeachingMaterial(@RequestBody TeachingMaterialQuery teachingMaterialQuery){


        LambdaQueryWrapper<TeachingMaterial> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(TeachingMaterial::getMaterialId);

        if(!"".equals(teachingMaterialQuery.getTitle())&& teachingMaterialQuery.getTitle()!=null){
            wrapper.like(TeachingMaterial::getTitle, teachingMaterialQuery.getTitle());
        }

        Page<TeachingMaterial> page = teachingMaterialService.page(
                new Page<>(
                        teachingMaterialQuery.getPageNum(),
                        teachingMaterialQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
