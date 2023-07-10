package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.course.mapper.ClassesMapper;
import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.entity.RolePermission;
import cn.hunnu.recommender.user.mapper.RolePermissionMapper;
import cn.hunnu.recommender.user.service.RolePermissionService;
import cn.hunnu.recommender.user.vo.RolePermissionVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
