package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.Resources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 学习资源表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Mapper
public interface ResourcesMapper extends BaseMapper<Resources> {
    @Select("select resource_id from resources order by resource_id desc ;")
    List<Integer> findID();

    @Select("select resource_id from resources where resource_type='视频' order by resource_id desc ;")
    List<Integer> findIDVideo();

    @Select("select resource_id from resources where resource_type='文档' order by resource_id desc ;")
    List<Integer> findIDDocumentation();
    
    List<Resources> findByTopicIdIn(List<Integer> resourceIds);

    @Select("select resource_type from resources where resource_id = #{resourceId}")
    String findResourceType(Integer resourceId);

    @Select("select resource_id from resources where knowledge_id = #{knowledgeId}")
    List<Integer> findResourcesId(Integer knowledgeId);
}
