package cn.hunnu.recommender.manager.service.impl;

import cn.hunnu.recommender.manager.dao.ManagerTestMapper;
import cn.hunnu.recommender.manager.entity.ManagerTest;
import cn.hunnu.recommender.manager.service.IManagerTestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerTestServiceImpl implements IManagerTestService {

    @Autowired
    ManagerTestMapper managerTestMapper;

    @Override
    public List<ManagerTest> getAllManagerTest() {
        return managerTestMapper.selectAll();
    }

    @Override
    public PageInfo<ManagerTest> getAllManagerTestByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);//改写语句实现分页查询
        List<ManagerTest> all = managerTestMapper.selectAll();
        PageInfo<ManagerTest> pageInfo = new PageInfo<>(all);
        return pageInfo;
    }
}
