package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.QuestionBankQuery;
import cn.hunnu.recommender.examination.entity.QuestionBank;
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
 * 题库表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "题库模块",tags = "题库模块")
@RestController
@RequestMapping("/question-bank")
public class QuestionBankController extends examinationBaseController {

    @ApiOperation(value = "题库列表",notes = "题库列表")
    @GetMapping("/list")
    public List<QuestionBank> list() {
        return questionBankService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result findPage(@RequestBody QuestionBankQuery questionBankQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<QuestionBank> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(QuestionBank::getBankId);

        if (!"".equals(questionBankQuery.getQuestionBankName()) && questionBankQuery.getQuestionBankName() != null) {
            wrapper.like(QuestionBank::getBankName,questionBankQuery.getQuestionBankName());
        }

        Page<QuestionBank> page = questionBankService.page(
                new Page<>(
                        questionBankQuery.getPageNum(),
                        questionBankQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody QuestionBank papers) {

//        throw new CustomException("这个是自定义异常");

        questionBankService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        questionBankService.removeByIds(ids);
        return Result.success();
    }

}
