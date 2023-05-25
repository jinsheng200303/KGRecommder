package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.entity.RolePermission;
import cn.hunnu.recommender.user.mapper.RolePermissionMapper;
import cn.hunnu.recommender.user.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
