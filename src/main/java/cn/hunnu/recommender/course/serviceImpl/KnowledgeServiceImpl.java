package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.Knowledge;
import cn.hunnu.recommender.course.mapper.KnowledgeMapper;
import cn.hunnu.recommender.course.service.KnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 知识点表 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements KnowledgeService {

}
