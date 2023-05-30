package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.KnowledgeQuery;
import cn.hunnu.recommender.course.entity.Homework;
import cn.hunnu.recommender.course.entity.Knowledge;
import cn.hunnu.recommender.course.service.KnowledgeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 知识点表 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@RestController
@RequestMapping("/knowledge")
@Api(value = "知识点模块",tags = "知识点模块")
public class KnowledgeController extends CourseBaseController {
    @ApiOperation(value = "知识点列表",notes = "知识点列表")
    @GetMapping("/list")
    public List<Knowledge> list() {
        return knowledgeService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "知识点新增/修改",notes = "知识点新增/修改")
    public Result save(@Validated @RequestBody Knowledge knowledge){
        knowledgeService.saveOrUpdate(knowledge);
        return Result.success();
    }

    //根据ID删除知识点
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除知识点",notes = "删除知识点")
    public Result delete(@RequestBody List<Integer> IDS){
        knowledgeService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "知识点查询",notes = "知识点查询")
    public Result<Page<Knowledge>> queryKnowledge(@RequestBody KnowledgeQuery knowledgeQuery){


        LambdaQueryWrapper<Knowledge> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Knowledge::getKnowledgeId);

        if(!"".equals(knowledgeQuery.getKnowledgeName())&& knowledgeQuery.getKnowledgeName()!=null){
            wrapper.like(Knowledge::getKnowledgeName, knowledgeQuery.getKnowledgeName());
        }

        Page<Knowledge> page = knowledgeService.page(
                new Page<>(
                        knowledgeQuery.getPageNum(),
                        knowledgeQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
