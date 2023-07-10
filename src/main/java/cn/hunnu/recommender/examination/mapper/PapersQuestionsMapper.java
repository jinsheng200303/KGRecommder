package cn.hunnu.recommender.examination.mapper;

import cn.hunnu.recommender.examination.entity.PapersQuestions;
import cn.hunnu.recommender.examination.entity.Questions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-10
 */
@Mapper
public interface PapersQuestionsMapper extends BaseMapper<PapersQuestions> {
    //根据试卷ID查找组成该试卷的试题详细信息
    List<Questions> selectQuestions(Integer paperId);
}
