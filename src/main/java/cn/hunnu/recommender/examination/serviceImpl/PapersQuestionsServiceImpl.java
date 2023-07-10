package cn.hunnu.recommender.examination.serviceImpl;

import cn.hunnu.recommender.examination.entity.PapersQuestions;
import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.mapper.PapersQuestionsMapper;
import cn.hunnu.recommender.examination.service.PapersQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-10
 */
@Service
public class PapersQuestionsServiceImpl extends ServiceImpl<PapersQuestionsMapper, PapersQuestions> implements PapersQuestionsService {

    @Resource
    private PapersQuestionsMapper papersQuestionsMapper;

    //根据试卷ID查找组成该试卷的试题详细信息
    @Override
    public List<Questions> selectQuestions(Integer paperId) {
        return papersQuestionsMapper.selectQuestions(paperId);
    }
}
