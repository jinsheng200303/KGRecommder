package cn.hunnu.recommender.app.rest;

import cn.hunnu.recommender.app.entity.AppTest;
import cn.hunnu.recommender.app.service.IAppTestService;
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
@RequestMapping(value = "/recommender/v1/app/test", produces = "application/json")
public class AppTestController {
    @Autowired
    IAppTestService appTestService;

    @GetMapping(value = "/getAll")
    public List<AppTest> getAllAppTest() {
        log.info("获取所有管理测试记录");
        return appTestService.getAllAppTest();
    }

    @GetMapping(value = "/getByPage")
    public PageInfo<AppTest> getAllAppTestByPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        log.info("分页获取AppTest，page:{} ,pageSize:{}", page, pageSize);
        return appTestService.getAllAppTestByPage(page,pageSize);
    }

    @GetMapping(value = "/getByPage11")
    public PageInfo<AppTest> getAllAppTestByPage111(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        log.info("分页获取AppTest11，page:{} ,pageSize:{}", page, pageSize);
        return appTestService.getAllAppTestByPage(page,pageSize);
    }

}
