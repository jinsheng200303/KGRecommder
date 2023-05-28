package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.Lesson;
import cn.hunnu.recommender.course.mapper.LessonMapper;
import cn.hunnu.recommender.course.service.LessonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {

}
