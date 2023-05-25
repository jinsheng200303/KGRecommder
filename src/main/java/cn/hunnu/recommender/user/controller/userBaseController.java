package cn.hunnu.recommender.user.controller;

import cn.hunnu.recommender.user.serviceImpl.PersonServiceImpl;
import cn.hunnu.recommender.user.serviceImpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class userBaseController {
    @Autowired
    PersonServiceImpl personService;

    @Autowired
    RoleServiceImpl roleService;
}
