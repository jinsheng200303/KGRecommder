package cn.hunnu.recommender.app.service;

import cn.hunnu.recommender.app.entity.AppTest;
import cn.hunnu.recommender.manager.entity.ManagerTest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 应用测试服务接口类
 */
public interface IAppTestService {

    /**
     * 获取所有应用测试记录
     * @return
     */
    List<AppTest> getAllAppTest();

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<AppTest> getAllAppTestByPage(int page, int pageSize);
}
