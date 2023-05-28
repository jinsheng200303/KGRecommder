package cn.hunnu.recommender.course.mapper;

import cn.hunnu.recommender.course.entity.Knowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 知识点表 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Mapper
public interface KnowledgeMapper extends BaseMapper<Knowledge> {

}
