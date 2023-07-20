package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.QuestionKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-18
 */
@Mapper
public interface QuestionKnowledgeMapper extends BaseMapper<QuestionKnowledge> {
    @Select("select * from question_knowledge order by question_knowledge_id desc limit 1;")
    QuestionKnowledge findLastRecords();

    @Select("select * from question_knowledge")
    List<QuestionKnowledge> findRecords();

    @Select("select * from question_knowledge where question_id = #{questionId}")
    QuestionKnowledge findRecord(Integer questionId);

    @Select("select * from question_knowledge")
    Cursor<QuestionKnowledge> selectCursor();
}
