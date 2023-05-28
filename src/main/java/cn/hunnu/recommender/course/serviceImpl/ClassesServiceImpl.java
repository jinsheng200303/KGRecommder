package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.mapper.ClassesMapper;
import cn.hunnu.recommender.course.service.ClassesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课堂 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

}
