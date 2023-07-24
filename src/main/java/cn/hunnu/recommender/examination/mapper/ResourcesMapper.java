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
    
    List<Resources> findByTopicIdIn(List<Integer> resourceIds);

}
