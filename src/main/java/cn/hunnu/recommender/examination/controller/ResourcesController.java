package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.ResourcesQuery;
import cn.hunnu.recommender.examination.entity.Records;
import cn.hunnu.recommender.examination.entity.Resources;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学习资源表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "学习资源模块",tags = "学习资源模块")
@RestController
@RequestMapping("/resources")
public class ResourcesController extends ExaminationBaseController {

    @ApiOperation(value = "学习资源列表",notes = "学习资源列表")
    @GetMapping("/list")
    public List<Resources> list() {
        return resourcesService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<Resources>> findPage(@RequestBody ResourcesQuery resourcesQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Resources> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Resources::getResourceId);

        if (!"".equals(resourcesQuery.getResourcesName()) && resourcesQuery.getResourcesName() != null) {
            wrapper.like(Resources::getResourceName, resourcesQuery.getResourcesName());
        }

        Page<Resources> page = resourcesService.page(
                new Page<>(
                        resourcesQuery.getPageNum(),
                        resourcesQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Resources resources) {

//        throw new CustomException("这个是自定义异常");

        resourcesService.saveOrUpdate(resources);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        resourcesService.removeByIds(ids);
        return Result.success();
    }

}
