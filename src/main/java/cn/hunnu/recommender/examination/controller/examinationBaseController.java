package cn.hunnu.recommender.examination.controller;

import cn.hunnu.recommender.examination.serviceImpl.ExamsServiceImpl;
import cn.hunnu.recommender.examination.serviceImpl.PapersServiceImpl;
import cn.hunnu.recommender.examination.serviceImpl.QuestionBankServiceImpl;
import cn.hunnu.recommender.examination.serviceImpl.QuestionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class examinationBaseController {
    @Autowired
    ExamsServiceImpl examsService;

    @Autowired
    PapersServiceImpl papersService;

    @Autowired
    QuestionBankServiceImpl questionBankService;

    @Autowired
    QuestionsServiceImpl questionsService;
}
