package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.Options;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 选项表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Mapper
public interface OptionsMapper extends BaseMapper<Options> {

    @Select("select * from options where question_id=#{questionId}")
    List<Options> selectByQuestionId(Integer questionId);

}
