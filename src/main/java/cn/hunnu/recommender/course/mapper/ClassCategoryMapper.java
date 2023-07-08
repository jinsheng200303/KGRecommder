package cn.hunnu.recommender.course.mapper;

import cn.hunnu.recommender.course.entity.ClassCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课堂类别关联表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-08
 */
@Mapper
public interface ClassCategoryMapper extends BaseMapper<ClassCategory> {

}
