package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.dto.QuestionsQuery;
import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.vo.QuestionVO;
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
    void delBatchQuestionKnowledges(List<Integer> ids);

    @Select("select * from options where question_id=#{questionId}")
    List<Options> findQuestionOptions(Integer questionId);

    Page<QuestionVO> findQuestions(Page<QuestionsQuery> objectPage,@Param("bankName") String bankName,@Param("questionStatement") String questionStatement);

    @Select("select * from questions where bank_id = #{bankId};")
    List<Questions> bankIdFindQuestions(Integer bankId);

    @Select("select question_id from questions order by question_id desc ;")
    List<Integer> findID();

    List<Questions> findByTopicIdIn(List<Integer> questionIds);

    @Select("select question_id from questions order by question_id desc limit 1")
    Integer findNewQuestionId();
}
