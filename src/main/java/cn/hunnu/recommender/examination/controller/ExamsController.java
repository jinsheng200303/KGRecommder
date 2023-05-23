package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.ExamsQuery;
import cn.hunnu.recommender.examination.dto.PageInfo;
import cn.hunnu.recommender.examination.entity.Exams;
import cn.hunnu.recommender.examination.serviceImpl.ExamsServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 考试表 前端控制器
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-19
 */
@RestController
@RequestMapping("/exams")
public class ExamsController extends examsBaseController {

    @GetMapping("/list")
    public List<Exams> list() {
        return examsService.list();
    }

    //分页查询 页码 每页显示多少条
    @PostMapping("/page")
    public Result findPage(@RequestBody ExamsQuery examsQuery) {
        //查出的数据降序排列，且支持名称模糊查询
        LambdaQueryWrapper<Exams> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Exams::getExamId);

        if (!"".equals(examsQuery.getExamTitle()) && examsQuery.getExamTitle() != null) {
            wrapper.like(Exams::getExamTitle,examsQuery.getExamTitle());
        }

        Page<Exams> page = examsService.page(
                new Page<>(
                        examsQuery.getPageNum(),
                        examsQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    //编辑和新增 id
    @PostMapping("/save")
    public Result save(@RequestBody Exams exams) {
        examsService.saveOrUpdate(exams);
        return Result.success();
    }

    //根据id来删除
    @PostMapping("/delBatch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        examsService.removeByIds(ids);
        return Result.success();
    }

}
