package cn.hunnu.recommender.manager.rest;

import cn.hunnu.recommender.manager.entity.ManagerTest;
import cn.hunnu.recommender.manager.service.IManagerTestService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/recommender/v1/manager/test", produces = "application/json")
public class ManagerTestController {
    @Autowired
    IManagerTestService managerTestService;

    @GetMapping(value = "/getAll")
    public List<ManagerTest> getAllMangerTest() {
        log.info("获取所有管理测试记录");
        return managerTestService.getAllManagerTest();
    }

    @GetMapping(value = "/getByPage")
    public PageInfo<ManagerTest> getAllManagerTestByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        log.info("分页获取ManagerTest，page:{} ,pageSize:{}", page, pageSize);
        return managerTestService.getAllManagerTestByPage(page,pageSize);
    }

}
