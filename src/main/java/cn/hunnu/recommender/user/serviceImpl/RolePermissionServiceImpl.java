package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.course.mapper.ClassesMapper;
import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.entity.Role;
import cn.hunnu.recommender.user.entity.RolePermission;
import cn.hunnu.recommender.user.mapper.RolePermissionMapper;
import cn.hunnu.recommender.user.service.RolePermissionService;
import cn.hunnu.recommender.user.vo.RolePermissionVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> findRolePermissions(Integer roleId,String permissionName) {
        return rolePermissionMapper.findRolePermissions(roleId,permissionName);
    }

    @Transactional
    @Override
    public void reviseRolePermissions(Integer roleId, List<Integer> permissionIds) {
        //先删除角色ID对应的所有权限
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        rolePermissionMapper.delete(queryWrapper);

        //再把前端传过来的权限ID绑定到当前的角色ID上去
        for (Integer permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
    }
}
