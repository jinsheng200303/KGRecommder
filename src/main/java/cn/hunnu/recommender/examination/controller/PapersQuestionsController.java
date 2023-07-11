package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.PapersQuestionsQuery;
import cn.hunnu.recommender.examination.entity.PapersQuestions;
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
 *  前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-10
 */
@Api(value = "试卷与试题关联模块",tags = "试卷与试题关联模块")
@RestController
@RequestMapping("/papers-questions")
public class PapersQuestionsController extends ExaminationBaseController {
    @ApiOperation(value = "试题与试卷关联列表",notes = "试题与试卷关联列表")
    @GetMapping("/list")
    public List<PapersQuestions> list() {
        return papersQuestionsService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<PapersQuestions>> findPage(@RequestBody PapersQuestionsQuery papersQuestionsQuery) {
        //查出的数据降序排列
        LambdaQueryWrapper<PapersQuestions> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PapersQuestions::getPaperQuestionId);


        Page<PapersQuestions> page = papersQuestionsService.page(
                new Page<>(
                        papersQuestionsQuery.getPageNum(),
                        papersQuestionsQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody PapersQuestions papersQuestions) {

//        throw new CustomException("这个是自定义异常");

        papersQuestionsService.saveOrUpdate(papersQuestions);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        papersQuestionsService.removeByIds(ids);
        return Result.success();
    }

}
