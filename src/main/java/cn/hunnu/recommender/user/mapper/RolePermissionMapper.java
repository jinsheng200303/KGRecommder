package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.entity.RolePermission;
import cn.hunnu.recommender.user.vo.RolePermissionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<Permission> findRolePermissions(@Param("roleId") Integer roleId,@Param("permissionName") String permissionName);
}
