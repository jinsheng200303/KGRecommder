package cn.hunnu.recommender.examination.serviceImpl;

import cn.hunnu.recommender.examination.entity.Options;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.mapper.QuestionsMapper;
import cn.hunnu.recommender.examination.service.QuestionsService;
import cn.hunnu.recommender.examination.vo.QuestionOptionsVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 试题表 服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-05-30
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsService {

    @Autowired
    QuestionsMapper questionsMapper;

    @Override
    public Page<Questions> queryQuestion(Page<Questions> objectPage, Integer bankId, String questionStatement) {
        return questionsMapper.queryQuestion(objectPage,bankId,questionStatement);
    }

    @Override
    public void delBatchQuestionsAndOptions(List<Integer> ids) {
        questionsMapper.delBatchQuestions(ids);
        questionsMapper.delBatchOptions(ids);
    }

    @Override
    public List<Options> findQuestionOptions(Integer questionId) {
        return questionsMapper.findQuestionOptions(questionId);
    }

}
