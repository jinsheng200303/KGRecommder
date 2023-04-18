package cn.hunnu.recommender.manager.dao;

import cn.hunnu.recommender.manager.entity.ManagerTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagerTestMapper {

    /**
     * 获取manager_test表中的所有记录
     *
     * @return
     */
    @Select("select * from manager_test order by id")
    List<ManagerTest> selectAll();

}