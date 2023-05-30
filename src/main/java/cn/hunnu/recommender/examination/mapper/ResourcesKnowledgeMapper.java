package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.ResourcesKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学习资源与知识点关联表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Mapper
public interface ResourcesKnowledgeMapper extends BaseMapper<ResourcesKnowledge> {

}
