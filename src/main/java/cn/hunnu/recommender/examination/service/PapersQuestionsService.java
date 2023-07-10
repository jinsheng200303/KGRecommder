package cn.hunnu.recommender.examination.service;

import cn.hunnu.recommender.examination.entity.PapersQuestions;
import cn.hunnu.recommender.examination.entity.Questions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-10
 */
public interface PapersQuestionsService extends IService<PapersQuestions> {

//    根据试卷ID查找组成该试卷的试题详细信息
    List<Questions> selectQuestions(Integer paperId);
}
