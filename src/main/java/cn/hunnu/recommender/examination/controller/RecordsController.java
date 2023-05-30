package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.RecordsQuery;
import cn.hunnu.recommender.examination.entity.RecordDiag;
import cn.hunnu.recommender.examination.entity.Records;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 做题记录表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "做题记录模块",tags = "做题记录模块")
@RestController
@RequestMapping("/records")
public class RecordsController extends ExaminationBaseController {

    @ApiOperation(value = "做题记录列表",notes = "做题记录列表")
    @GetMapping("/list")
    public List<Records> list() {
        return recordsService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<Records>> findPage(@RequestBody RecordsQuery recordsQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Records> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Records::getRecordId);

        if (!"".equals(recordsQuery.getRecordAnswer()) && recordsQuery.getRecordAnswer() != null) {
            wrapper.like(Records::getAnswer, recordsQuery.getRecordAnswer());
        }

        Page<Records> page = recordsService.page(
                new Page<>(
                        recordsQuery.getPageNum(),
                        recordsQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Records papers) {

//        throw new CustomException("这个是自定义异常");

        recordsService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        recordsService.removeByIds(ids);
        return Result.success();
    }

}
