package cn.hunnu.recommender.examination.controller;

import cn.hunnu.recommender.examination.serviceImpl.*;
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

    @Autowired
    OptionsServiceImpl optionsService;
}
