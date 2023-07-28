package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.Records;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 做题记录表 Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Mapper
public interface RecordsMapper extends BaseMapper<Records> {

    @Select("select question_id from records where user_id = #{userId} ;")
    List<Integer> getQuestionListByUserID(Integer userId);

    @Select("select * from records where user_id = #{userId} and question_id = #{questionId}")
    Records findRecord(Integer userId,Integer questionId);
}
