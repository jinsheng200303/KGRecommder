package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.entity.Knowledge;
import cn.hunnu.recommender.user.entity.PersonKnowledge;
import cn.hunnu.recommender.user.entity.PersonStylePaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户知识点理解程度关联表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-07-20
 */
@Mapper
public interface PersonKnowledgeMapper extends BaseMapper<PersonKnowledge> {

    List<Knowledge> selectKnowledge(Integer userId);

    @Select("select knowledge_id from resources where resource_id = #{resourceId};")
    Integer findKnowledgeId(Integer resourceId);

    @Select("select knowledge_id from question_knowledge where question_id = #{questionId};")
    Integer findQuestionKnowledgeId(Integer questionId);

    @Select("select * from person_knowledge where user_id = #{userId} and knowledge_id = #{knowledgeId};")
    PersonKnowledge findComprehension(Integer userId,Integer knowledgeId);

    @Select("select knowledge_id from person_knowledge where user_id = #{userId} and comprehension = #{comprehension} limit 1;")
    Integer findLeastKnowledgeId(Integer userId,double comprehension);

    @Select("select MIN(comprehension) from person_knowledge where user_id = #{userId};")
    double findLeastComprehension(Integer userId);

    @Select("select user_id from person_knowledge where user_id = #{userId} limit 1;")
    Integer findUser(Integer userId);
}
