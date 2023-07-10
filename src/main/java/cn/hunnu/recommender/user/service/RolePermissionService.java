package cn.hunnu.recommender.user.service;

import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.entity.RolePermission;
import cn.hunnu.recommender.user.vo.RolePermissionVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 服务类
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
public interface RolePermissionService extends IService<RolePermission> {

    List<Permission> findRolePermissions(Integer roleId,String permissionName);
}
