package cn.hunnu.recommender.user.service;

import cn.hunnu.recommender.user.dto.UserLoginDTO;
import cn.hunnu.recommender.user.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 个人信息 服务类
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
public interface PersonService extends IService<Person> {
    UserLoginDTO register(UserLoginDTO userLoginDTO);

    void sendEmailCode(String email);
}
