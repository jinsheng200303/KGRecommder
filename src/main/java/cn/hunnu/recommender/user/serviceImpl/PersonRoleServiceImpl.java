package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.mapper.PersonRoleMapper;
import cn.hunnu.recommender.user.service.PersonRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人角色关联表 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Service
public class PersonRoleServiceImpl extends ServiceImpl<PersonRoleMapper, PersonRole> implements PersonRoleService {

}
