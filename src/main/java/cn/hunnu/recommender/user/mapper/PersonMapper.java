package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.vo.UserRoleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 个人信息 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

    Page<UserRoleVO> getUserNameRole(Page<UserRoleVO> page,@Param("userName") String userName, @Param("roleName") String roleName);

}
