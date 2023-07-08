package cn.hunnu.recommender.user.service;

import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.entity.PersonRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 个人角色关联表 服务类
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
public interface PersonRoleService extends IService<PersonRole> {

    void reviseUserRole(Integer personRoleId, Integer roleId);

    PersonRole findUserRole(Integer userId, Integer roleId);

    void addUserRole(Integer userId, Integer roleId);

    int findUserId(Integer userId);

    int findUsersRole(Integer userId);
}
