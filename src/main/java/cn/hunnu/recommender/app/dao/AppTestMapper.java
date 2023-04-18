package cn.hunnu.recommender.app.dao;

import cn.hunnu.recommender.app.entity.AppTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppTestMapper {

    /**
     * 获取app_test表中的所有记录
     *
     * @return
     */
    @Select("select * from app_test order by id")
    List<AppTest> selectAll();

}