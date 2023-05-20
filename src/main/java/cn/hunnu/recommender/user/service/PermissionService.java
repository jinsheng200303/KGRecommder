package cn.hunnu.recommender.user.service;

import cn.hunnu.recommender.user.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author czj
 * @since 2023-05-19
 */
public interface PermissionService extends IService<Permission> {

    Map<String, Object> tt(Permission permission);
}
