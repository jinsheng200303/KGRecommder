package cn.hunnu.recommender.user.service;

import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.dto.UserLoginDTO;
import cn.hunnu.recommender.user.dto.UserRegisterDTO;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.vo.UserRoleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 个人信息 服务类
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
public interface PersonService extends IService<Person> {
    UserRegisterDTO register(UserRegisterDTO userRegisterDTO);

    boolean sendEmailCode(String email);

    Page<UserRoleVO> getUserNameRole(Page<UserRoleVO> page, String userName, String roleName);

}
