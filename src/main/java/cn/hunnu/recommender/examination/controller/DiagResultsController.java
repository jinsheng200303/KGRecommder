package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.DiagResultsQuery;
import cn.hunnu.recommender.examination.entity.DiagResults;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 诊断结果表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "认知诊断结果模块",tags = "认知诊断结果模块")
@RestController
@RequestMapping("/diag-results")
public class DiagResultsController extends ExaminationBaseController {

    @ApiOperation(value = "认知诊断结果列表",notes = "认知诊断结果列表")
    @GetMapping("/list")
    public List<DiagResults> list() {
        return diagResultsService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result findPage(@RequestBody DiagResultsQuery diagResultsQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<DiagResults> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(DiagResults::getResultId);

        if (!"".equals(diagResultsQuery.getDiagResultsSuggestion()) && diagResultsQuery.getDiagResultsSuggestion() != null) {
            wrapper.like(DiagResults::getSuggestion, diagResultsQuery.getDiagResultsSuggestion());
        }

        Page<DiagResults> page = diagResultsService.page(
                new Page<>(
                        diagResultsQuery.getPageNum(),
                        diagResultsQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody DiagResults papers) {

//        throw new CustomException("这个是自定义异常");

        diagResultsService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        diagResultsService.removeByIds(ids);
        return Result.success();
    }
}
