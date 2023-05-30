package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.Homework;
import cn.hunnu.recommender.course.mapper.HomeworkMapper;
import cn.hunnu.recommender.course.service.HomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作业 服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {

}
