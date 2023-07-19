package cn.hunnu.recommender.user.mapper;

import cn.hunnu.recommender.user.entity.PersonKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
