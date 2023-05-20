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

    @Override
    public Map<String, Object> tt(Permission permission) {
        //根据用户名密码进行查询（测试）
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPermissionName,permission.getPermissionName());
        wrapper.eq(Permission::getPermissionId,permission.getPermissionId());
        Permission testPermission = this.baseMapper.selectOne(wrapper);
        //结果不为空，则生成Token，并将信息存入redis
        if(testPermission != null){
            //暂时使用UUID，后续jwt
            String key = "成功暂时用此替代"+ UUID.randomUUID();

            //存入redis

            //返回数据
            Map<String,Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }
}
