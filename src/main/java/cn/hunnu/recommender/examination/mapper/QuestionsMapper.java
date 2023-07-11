package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.vo.QuestionOptionsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    Page<Questions> queryQuestion(Page<Questions> objectPage,@Param("bankId") Integer bankId,@Param("questionStatement") String questionStatement);

    void delBatchQuestions(List<Integer> ids);
    void delBatchOptions(List<Integer> ids);

    @Select("select * from options where question_id=#{questionId}")
    List<Options> findQuestionOptions(Integer questionId);
}
