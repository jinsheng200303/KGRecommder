package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.ExamsQuery;
import cn.hunnu.recommender.examination.dto.UserGradeQuery;
import cn.hunnu.recommender.examination.entity.Exams;
import cn.hunnu.recommender.examination.entity.UserGrade;
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
 * 学生成绩表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-05
 */

@Api(value = "学生成绩模块",tags = "学生成绩模块")
@RestController
@RequestMapping("/user-grade")
public class UserGradeController extends ExaminationBaseController {

    @ApiOperation(value = "学生成绩列表",notes = "学生成绩列表")
    @GetMapping("/list")
    public List<UserGrade> list() {
        return userGradeService.list();
    }

    //分页查询 页码 每页显示多少条
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("/page")
    public Result<Page<UserGrade>> findPage(@RequestBody UserGradeQuery userGradeQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<UserGrade> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(UserGrade::getUserId);

        if (!"".equals(userGradeQuery.getUserId()) && userGradeQuery.getUserId() != null) {
            wrapper.like(UserGrade::getUserId,userGradeQuery.getUserId());
        }

        if (!"".equals(userGradeQuery.getExamsId()) && userGradeQuery.getExamsId() != null) {
            wrapper.like(UserGrade::getExamsId,userGradeQuery.getExamsId());
        }

        Page<UserGrade> page = userGradeService.page(
                new Page<>(
                        userGradeQuery.getPageNum(),
                        userGradeQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody UserGrade userGrade) {

//        throw new CustomException("这个是自定义异常");

        userGradeService.saveOrUpdate(userGrade);
        return Result.success();
    }

    //根据id来进行批量删除
    @ApiOperation(value = "数据根据id批量删除",notes = "数据根据id批量删除")
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        userGradeService.removeByIds(ids);
        return Result.success();
    }
}
