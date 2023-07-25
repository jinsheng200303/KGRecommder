package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.course.entity.Knowledge;
import cn.hunnu.recommender.user.entity.PersonKnowledge;
import cn.hunnu.recommender.user.mapper.PersonKnowledgeMapper;
import cn.hunnu.recommender.user.service.PersonKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private PersonKnowledgeMapper personKnowledgeMapper;
    @Override
    public List<Knowledge> selectKnowledge(Integer userId) {
        return personKnowledgeMapper.selectKnowledge(userId);
    }

    @Override
    public double findComprehension(Integer userId, Integer resourceId) {
        int knowledgeId = personKnowledgeMapper.findKnowledgeId(resourceId);
        PersonKnowledge personKnowledge = personKnowledgeMapper.findComprehension(userId, knowledgeId);
        if(personKnowledge == null){
            return 0;
        }else {
            return personKnowledge.getComprehension();
        }

    }

    @Override
    public double findQuestionComprehension(Integer userId, Integer questionId) {
        int knowledgeId = personKnowledgeMapper.findQuestionKnowledgeId(questionId);
        PersonKnowledge personKnowledge = personKnowledgeMapper.findComprehension(userId, knowledgeId);
        if(personKnowledge == null){
            return 0;
        }else {
            return personKnowledge.getComprehension();
        }
    }

    @Override
    public Integer findLeastKnowledgeId(Integer userId){
        if(personKnowledgeMapper.findUser(userId)!=null){
            double comprehension = personKnowledgeMapper.findLeastComprehension(userId);
            Integer knowledgeId = personKnowledgeMapper.findLeastKnowledgeId(userId,comprehension);
            return knowledgeId;
        }else {
            return 0;
        }
    }
}
