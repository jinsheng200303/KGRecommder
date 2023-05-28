package cn.hunnu.recommender.course.controller;

import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.LessonQuery;
import cn.hunnu.recommender.course.entity.Lesson;
import cn.hunnu.recommender.course.service.LessonService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@RestController
@RequestMapping("/lesson")
@Api(value = "课程模块",tags = "课程模块")
public class LessonController extends CourseBaseController {
    @ApiOperation(value = "课程列表",notes = "课程列表")
    @GetMapping("/list")
    public List<Lesson> list() {
        return lessonService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课程新增/修改",notes = "课程新增/修改")
    public Result save(@Validated @RequestBody Lesson lesson){
        lessonService.saveOrUpdate(lesson);
        return Result.success();
    }

    //根据ID删除课程
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课程",notes = "删除课程")
    public Result delete(@RequestBody List<Integer> IDS){
        lessonService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课程查询",notes = "课程查询")
    public Result queryLesson(@RequestBody LessonQuery lessonQuery){


        LambdaQueryWrapper<Lesson> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Lesson::getLessonId);

        if(!"".equals(lessonQuery.getLessonName())&& lessonQuery.getLessonName()!=null){
            wrapper.like(Lesson::getLessonName, lessonQuery.getLessonName());
        }

        Page<Lesson> page = lessonService.page(
                new Page<>(
                        lessonQuery.getPageNum(),
                        lessonQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
