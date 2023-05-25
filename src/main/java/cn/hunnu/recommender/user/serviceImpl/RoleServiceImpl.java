package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.entity.Role;
import cn.hunnu.recommender.user.mapper.RoleMapper;
import cn.hunnu.recommender.user.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
