package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.mapper.PermissionMapper;
import cn.hunnu.recommender.user.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
