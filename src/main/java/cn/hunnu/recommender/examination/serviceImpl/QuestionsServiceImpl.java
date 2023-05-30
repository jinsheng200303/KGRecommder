package cn.hunnu.recommender.examination.serviceImpl;

import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.mapper.QuestionsMapper;
import cn.hunnu.recommender.examination.service.QuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试题表 服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsService {

}
