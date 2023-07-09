package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.CategoryQuery;
import cn.hunnu.recommender.course.entity.ClassUser;
import cn.hunnu.recommender.course.entity.Category;
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
 * 课堂类别 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "课堂类别模块",tags = "课堂类别模块")
@RestController
@RequestMapping("/category")
public class CategoryController extends CourseBaseController {

    @ApiOperation(value = "课堂类别列表",notes = "课堂类别列表")
    @GetMapping("/list")
    public List<Category> list() {
        return categoryService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<Category>> findPage(@RequestBody CategoryQuery categoryQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Category::getCategoryId);

        if (!"".equals(categoryQuery.getCategoryName()) && categoryQuery.getCategoryName() != null) {
            wrapper.like(Category::getCategoryName, categoryQuery.getCategoryName());
        }

        Page<Category> page = categoryService.page(
                new Page<>(
                        categoryQuery.getPageNum(),
                        categoryQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Category papers) {

//        throw new CustomException("这个是自定义异常");

        categoryService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        categoryService.removeByIds(ids);
        return Result.success();
    }
}
