package cn.hunnu.recommender.course.controller;

import cn.hunnu.recommender.course.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseBaseController {
    @Autowired
    AnnouncementServiceImpl announcementService;

    @Autowired
    ClassesServiceImpl classesService;

    @Autowired
    KnowledgeServiceImpl knowledgeService;

    @Autowired
    LessonServiceImpl lessonService;

    @Autowired
    ClassAnnouncementServiceImpl classAnnouncementService;

    @Autowired
    ClassHomeworkServiceImpl classHomeworkService;

    @Autowired
    ClassResourceServiceImpl classResourceService;
}
