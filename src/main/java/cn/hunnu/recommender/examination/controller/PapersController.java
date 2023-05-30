package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.PapersQuery;
import cn.hunnu.recommender.examination.entity.Papers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.hunnu.recommender.examination.controller.examinationBaseController;

import java.util.List;

/**
 * <p>
 * 试卷表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "试卷模块",tags = "试卷模块")
@RestController
@RequestMapping("/papers")
public class PapersController extends examinationBaseController {

    @ApiOperation(value = "试卷列表",notes = "试卷列表")
    @GetMapping("/list")
    public List<Papers> list() {
        return papersService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result findPage(@RequestBody PapersQuery papersQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Papers> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Papers::getExamId);

        if (!"".equals(papersQuery.getPaperTitle()) && papersQuery.getPaperTitle() != null) {
            wrapper.like(Papers::getPaperTitle,papersQuery.getPaperTitle());
        }

        Page<Papers> page = papersService.page(
                new Page<>(
                        papersQuery.getPageNum(),
                        papersQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Papers papers) {

//        throw new CustomException("这个是自定义异常");

        papersService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        papersService.removeByIds(ids);
        return Result.success();
    }

}
