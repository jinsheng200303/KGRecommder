package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.ClassAnnouncement;
import cn.hunnu.recommender.course.mapper.ClassAnnouncementMapper;
import cn.hunnu.recommender.course.service.ClassAnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课堂公告 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@Service
public class ClassAnnouncementServiceImpl extends ServiceImpl<ClassAnnouncementMapper, ClassAnnouncement> implements ClassAnnouncementService {

}
