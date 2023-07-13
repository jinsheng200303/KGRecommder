package cn.hunnu.recommender.examination.service;

import cn.hunnu.recommender.examination.dto.QuestionsQuery;
import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.vo.QuestionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 试题表 服务类
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
public interface QuestionsService extends IService<Questions> {

    Page<Questions> queryQuestion(Page<Questions> objectPage, Integer bankId, String questionStatement);

    void delBatchQuestionsAndOptions(List<Integer> ids);

    List<Options> findQuestionOptions(Integer questionId);

    Page<QuestionVO> findQuestions(Page<QuestionsQuery> objectPage, String bankName, String questionStatement);

    List<Questions> bankIdFindQuestions(Integer bankId);
}
