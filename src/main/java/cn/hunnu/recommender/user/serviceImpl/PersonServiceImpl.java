package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.mapper.PersonMapper;
import cn.hunnu.recommender.user.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人信息 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
