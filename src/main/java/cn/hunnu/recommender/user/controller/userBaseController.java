package cn.hunnu.recommender.user.controller;

import cn.hunnu.recommender.user.service.ValidationService;
import cn.hunnu.recommender.user.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class userBaseController {
    @Autowired
    PersonServiceImpl personService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PermissionServiceImpl permissionService;

    @Autowired
    PersonRoleServiceImpl personRoleService;

    @Autowired
    RolePermissionServiceImpl rolePermissionService;

    @Autowired
    ValidationServiceImpl validationService;

    @Autowired
    PersonKnowledgeServiceImpl personKnowledgeService;

    @Autowired
    PersonStylePaperServiceImpl personStylePaperService;

    @Autowired
    StudyStyleServiceImpl studyStyleService;
}
