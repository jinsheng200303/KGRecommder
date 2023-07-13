package cn.hunnu.recommender.course.mapper;

import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.vo.ClassInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    Page<ClassInfoVO> classInfoQuery(Page<ClassInfoVO> page, @Param("className") String className, @Param("userName") String userName, @Param("categoryName") String categoryName);

    void addClass(@Param("userId") Integer userId,@Param("className") String className,@Param("categoryId") String classCategoryId);

    @Select("select * from classes where create_user_id = #{createUserId};")
    List<Classes> getByCreateUserId(Integer createUserId);
}
