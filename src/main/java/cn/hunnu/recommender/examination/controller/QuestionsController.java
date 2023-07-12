package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.QuestionsQuery;
import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.examination.entity.QuestionBank;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.service.QuestionsService;
import cn.hunnu.recommender.examination.vo.QuestionOptionsVO;
import cn.hunnu.recommender.examination.vo.QuestionVO;
import cn.hunnu.recommender.user.vo.UserRoleVO;
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

        //根据试题描述查找试题
        if (!"".equals(questionsQuery.getQuestionStatement()) && questionsQuery.getQuestionStatement() != null) {
            wrapper.like(Questions::getQuestionStatement, questionsQuery.getQuestionStatement());
        }

        //根据题库ID查找试题
        if (!"".equals(questionsQuery.getBankId()) && questionsQuery.getBankId() != null) {
            wrapper.eq(Questions::getBankId, questionsQuery.getBankId());
        }

        //根据试题类型查找试题
        if (!"".equals(questionsQuery.getType()) && questionsQuery.getType() != null) {
            wrapper.eq(Questions::getQuestionTypeId, questionsQuery.getType());
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
    public Result save(@Validated @RequestBody Questions questions) {

//        throw new CustomException("这个是自定义异常");

        questionsService.saveOrUpdate(questions);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        questionsService.removeByIds(ids);
        return Result.success();
    }

    @PostMapping("/questionAndOptions")
    @ApiOperation(value = "题库试题数组查询",notes = "题库试题数组查询")
    public Result queryQuestionOptionsInfo(@RequestBody QuestionsQuery questionsQuery){

        Page<Questions> page = questionsService.queryQuestion(new Page<Questions>(questionsQuery.getPageNum(),questionsQuery.getPageSize()),questionsQuery.getBankId(), questionsQuery.getQuestionStatement());
        return Result.success(page);
    }

    //根据id来进行批量删除试题以及其对应的选项
    @ApiOperation(value = "试题及选项根据id批量删除",notes = "试题及选项根据id批量删除")
    @PostMapping("/delBatchQuestionsAndOptions")
    public Result delBatchQuestionsAndOptions(@RequestBody List<Integer> ids) {
        questionsService.delBatchQuestionsAndOptions(ids);
        return Result.success();
    }

    //根据题目id来查询其对应的选项
    @ApiOperation(value = "根据id查询单个试题选项",notes = "根据id查询单个试题选项")
    @PostMapping("/findQuestionOptions")
    public Result findQuestionOptions(@RequestBody Integer questionId) {
        List<Options> options = questionsService.findQuestionOptions(questionId);
        return Result.success(options);
    }

    //分页查询试题信息
    @ApiOperation(value = "试题信息分页查询",notes = "试题信息分页查询")
    @PostMapping("/findQuestions")
    public Result findQuestions(@RequestBody QuestionsQuery questionsQuery) {
        Page<QuestionVO> questionVOPage = questionsService.findQuestions(
                new Page<QuestionsQuery>(questionsQuery.getPageNum(),questionsQuery.getPageSize()),
                questionsQuery.getBankName(),questionsQuery.getQuestionStatement());
        return Result.success(questionVOPage);
    }
}
