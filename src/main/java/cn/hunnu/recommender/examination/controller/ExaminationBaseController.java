package cn.hunnu.recommender.examination.controller;

import cn.hunnu.recommender.examination.service.PapersQuestionsService;
import cn.hunnu.recommender.examination.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExaminationBaseController {
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

    @Autowired
    RecordsServiceImpl recordsService;

    @Autowired
    ResourcesKnowledgeServiceImpl resourcesKnowledgeService;

    @Autowired
    ResourcesServiceImpl resourcesService;

    @Autowired
    DiagResultsServiceImpl diagResultsService;

    @Autowired
    RecordDiagServiceImpl recordDiagService;

    @Autowired
    UserGradeServiceImpl userGradeService;

    @Autowired
    PapersQuestionsServiceImpl papersQuestionsService;
}
