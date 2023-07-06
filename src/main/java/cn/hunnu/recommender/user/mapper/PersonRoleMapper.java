package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.entity.PersonRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 个人角色关联表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Mapper
public interface PersonRoleMapper extends BaseMapper<PersonRole> {


    void reviseUserRole(@Param("personRoleId") Integer personRoleId,@Param("roleId") Integer roleId);

    PersonRole findUserRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    void addUserRole(Integer userId, Integer roleId);

    int findUserId(@Param("userId") Integer userId);

}
