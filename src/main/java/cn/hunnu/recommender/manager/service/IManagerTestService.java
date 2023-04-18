package cn.hunnu.recommender.manager.service;

import cn.hunnu.recommender.manager.entity.ManagerTest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 管理测试服务接口类
 */
public interface IManagerTestService {

    /**
     * 获取所有管理测试记录
     * @return
     */
    List<ManagerTest> getAllManagerTest();

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<ManagerTest> getAllManagerTestByPage(int page, int pageSize);
}
