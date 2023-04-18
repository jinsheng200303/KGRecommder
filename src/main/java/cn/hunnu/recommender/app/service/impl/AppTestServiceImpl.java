package cn.hunnu.recommender.app.service.impl;

import cn.hunnu.recommender.app.dao.AppTestMapper;
import cn.hunnu.recommender.app.entity.AppTest;
import cn.hunnu.recommender.app.service.IAppTestService;
import cn.hunnu.recommender.manager.dao.ManagerTestMapper;
import cn.hunnu.recommender.manager.entity.ManagerTest;
import cn.hunnu.recommender.manager.service.IManagerTestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppTestServiceImpl implements IAppTestService {

    @Autowired
    AppTestMapper appTestMapper;

    @Override
    public List<AppTest> getAllAppTest() {
        return appTestMapper.selectAll();
    }

    @Override
    public PageInfo<AppTest> getAllAppTestByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);//改写语句实现分页查询
        List<AppTest> all = appTestMapper.selectAll();
        PageInfo<AppTest> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }
}
