package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.entity.PersonKnowledge;
import cn.hunnu.recommender.user.mapper.PersonKnowledgeMapper;
import cn.hunnu.recommender.user.service.PersonKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户知识点理解程度关联表 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-07-20
 */
@Service
public class PersonKnowledgeServiceImpl extends ServiceImpl<PersonKnowledgeMapper, PersonKnowledge> implements PersonKnowledgeService {

}
