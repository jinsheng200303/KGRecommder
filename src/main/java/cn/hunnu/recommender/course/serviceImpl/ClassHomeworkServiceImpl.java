package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.ClassHomework;
import cn.hunnu.recommender.course.mapper.ClassHomeworkMapper;
import cn.hunnu.recommender.course.service.ClassHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课堂作业 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@Service
public class ClassHomeworkServiceImpl extends ServiceImpl<ClassHomeworkMapper, ClassHomework> implements ClassHomeworkService {

}
