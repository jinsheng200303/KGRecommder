package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.PersonRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 个人角色关联表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@Mapper
public interface PersonRoleMapper extends BaseMapper<PersonRole> {

}
