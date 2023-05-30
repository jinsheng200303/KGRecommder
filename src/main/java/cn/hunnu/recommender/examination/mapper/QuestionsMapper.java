package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.Questions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 试题表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Mapper
public interface QuestionsMapper extends BaseMapper<Questions> {

}
