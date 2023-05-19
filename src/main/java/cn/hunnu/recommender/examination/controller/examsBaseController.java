package cn.hunnu.recommender.examination.controller;

import cn.hunnu.recommender.examination.serviceImpl.ExamsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class examsBaseController {
    @Autowired
    ExamsServiceImpl examsService;
}
