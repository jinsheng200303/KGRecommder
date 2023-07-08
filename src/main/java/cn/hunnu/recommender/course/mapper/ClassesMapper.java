package cn.hunnu.recommender.course.mapper;

import cn.hunnu.recommender.course.entity.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 课堂 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {

    //查询用户ID对应的角色
    @Select("select role_id from person_role where user_id = #{userId}")
    Integer findUsersRole(Integer userId);
}
