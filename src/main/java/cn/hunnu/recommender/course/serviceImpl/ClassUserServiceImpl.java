package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.ClassUser;
import cn.hunnu.recommender.course.mapper.ClassUserMapper;
import cn.hunnu.recommender.course.service.ClassUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课堂用户 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-30
 */
@Service
public class ClassUserServiceImpl extends ServiceImpl<ClassUserMapper, ClassUser> implements ClassUserService {

//    public Page<ClassUser> selectPage(){
//        LambdaQueryWrapper<ClassUser> wrapper = Wrappers.lambdaQuery(ClassUser.class);
//        Page<ClassUser> ClassPage = this.page(new Page<>(1,3),wrapper);
//        return ClassPage;
//    }
}
