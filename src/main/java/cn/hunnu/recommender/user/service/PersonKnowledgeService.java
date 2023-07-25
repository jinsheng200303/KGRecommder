package cn.hunnu.recommender.user.service;

import cn.hunnu.recommender.course.entity.Knowledge;
import cn.hunnu.recommender.user.entity.PersonKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户知识点理解程度关联表 服务类
 * </p>
 *
 * @author czj
 * @since 2023-07-20
 */
public interface PersonKnowledgeService extends IService<PersonKnowledge> {
//根据用户ID查询其知识点掌握情况
    List<Knowledge> selectKnowledge(Integer userId);

    double findComprehension(Integer userId, Integer resourceId);

    double findQuestionComprehension(Integer userId, Integer resourceId);

    Integer findLeastKnowledgeId(Integer userId);
}
