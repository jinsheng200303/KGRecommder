package cn.hunnu.recommender.examination.controller;


import cn.hunnu.recommender.examination.entity.Exams;
import cn.hunnu.recommender.examination.serviceImpl.ExamsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ExamsServiceImpl examsService;

    @GetMapping("/list")
    public List<Exams> list(){
        return examsService.list();
    }
}
