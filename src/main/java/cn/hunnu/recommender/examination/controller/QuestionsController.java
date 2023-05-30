package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.QuestionsQuery;
import cn.hunnu.recommender.examination.entity.QuestionBank;
import cn.hunnu.recommender.examination.entity.Questions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 试题表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Api(value = "试题模块",tags = "试题模块")
@RestController
@RequestMapping("/questions")
public class QuestionsController extends ExaminationBaseController {

    @ApiOperation(value = "试题列表",notes = "试题列表")
    @GetMapping("/list")
    public List<Questions> list() {
        return questionsService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<Questions>> findPage(@RequestBody QuestionsQuery questionsQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Questions> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Questions::getQuestionId);

        if (!"".equals(questionsQuery.getQuestionStatement()) && questionsQuery.getQuestionStatement() != null) {
            wrapper.like(Questions::getQuestionStatement, questionsQuery.getQuestionStatement());
        }

        Page<Questions> page = questionsService.page(
                new Page<>(
                        questionsQuery.getPageNum(),
                        questionsQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Questions papers) {

//        throw new CustomException("这个是自定义异常");

        questionsService.saveOrUpdate(papers);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        questionsService.removeByIds(ids);
        return Result.success();
    }
}
