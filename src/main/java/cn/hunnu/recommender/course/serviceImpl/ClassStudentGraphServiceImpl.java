package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.ClassStudentGraph;
import cn.hunnu.recommender.course.mapper.ClassStudentGraphMapper;
import cn.hunnu.recommender.course.service.ClassStudentGraphService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课堂学生图谱 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@Service
public class ClassStudentGraphServiceImpl extends ServiceImpl<ClassStudentGraphMapper, ClassStudentGraph> implements ClassStudentGraphService {

}
