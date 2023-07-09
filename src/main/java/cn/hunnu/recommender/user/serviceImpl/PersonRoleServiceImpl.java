package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.mapper.PersonRoleMapper;
import cn.hunnu.recommender.user.service.PersonRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 个人角色关联表 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Service
public class PersonRoleServiceImpl extends ServiceImpl<PersonRoleMapper, PersonRole> implements PersonRoleService {

    @Autowired
    private PersonRoleMapper personRoleMapper;

    @Override
    public void reviseUserRole(Integer personRoleId, Integer roleId) {
        personRoleMapper.reviseUserRole(personRoleId,roleId);
    }

    //查找用户对应的角色是否存在
    @Override
    public PersonRole findUserRole(Integer userId, Integer roleId) {
        return personRoleMapper.findUserRole(userId,roleId);
    }

    @Override
    public void addUserRole(Integer userId, Integer roleId) {
        personRoleMapper.addUserRole(userId,roleId);
    }

    @Override
    public int findUserId(Integer userId) {
        return personRoleMapper.findUserId(userId);
    }

    @Override
    public int findUsersRole(Integer userId) {
        return personRoleMapper.findUsersRole(userId);
    }
}
