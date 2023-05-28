package cn.hunnu.recommender.course.mapper;

import cn.hunnu.recommender.course.entity.Lesson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Mapper
public interface LessonMapper extends BaseMapper<Lesson> {

}
