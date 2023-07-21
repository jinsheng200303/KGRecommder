package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.course.entity.Knowledge;
import cn.hunnu.recommender.user.entity.PersonKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}
