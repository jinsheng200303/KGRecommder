package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
