package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.Announcement;
import cn.hunnu.recommender.course.mapper.AnnouncementMapper;
import cn.hunnu.recommender.course.service.AnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-26
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

}
