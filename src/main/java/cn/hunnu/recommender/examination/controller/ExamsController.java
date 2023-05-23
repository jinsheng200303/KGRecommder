package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.dto.PageInfo;
import cn.hunnu.recommender.examination.entity.Exams;
import cn.hunnu.recommender.examination.serviceImpl.ExamsServiceImpl;
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
    public List<Exams> list(){
        return examsService.list();
    }

    //分页查询 页码 每页显示多少条
    @PostMapping("/page")
    public Result findPage(@RequestBody PageInfo pageInfo){
        Page<Exams> page = examsService.page(
                new Page<>(
                        pageInfo.getPageNum(),
                        pageInfo.getPageSize()
                )
        );
        return Result.success(page);
    }
}
