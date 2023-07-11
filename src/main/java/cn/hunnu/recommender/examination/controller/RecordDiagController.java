package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.RecordDiagQuery;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.entity.RecordDiag;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.hunnu.recommender.examination.controller.ExaminationBaseController;

import java.util.List;

/**
 * <p>
 * 记录与诊断关联表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "做题记录与诊断结果关联模块",tags = "做题记录与诊断结果关联模块")
@RestController
@RequestMapping("/record-diag")
public class RecordDiagController extends ExaminationBaseController {

    @ApiOperation(value = "做题记录与诊断结果关联列表",notes = "做题记录与诊断结果关联列表")
    @GetMapping("/list")
    public List<RecordDiag> list() {
        return recordDiagService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<RecordDiag>> findPage(@RequestBody RecordDiagQuery recordDiagQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<RecordDiag> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(RecordDiag::getRecordDiagId);

        if (recordDiagQuery.getRecordDiagId() != null) {
            wrapper.eq(RecordDiag::getRecordDiagId, recordDiagQuery.getRecordDiagId());
        }

        Page<RecordDiag> page = recordDiagService.page(
                new Page<>(
                        recordDiagQuery.getPageNum(),
                        recordDiagQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody RecordDiag recordDiag) {

//        throw new CustomException("这个是自定义异常");

        recordDiagService.saveOrUpdate(recordDiag);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        recordDiagService.removeByIds(ids);
        return Result.success();
    }
}
