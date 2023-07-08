package cn.hunnu.recommender.examination.service;

import cn.hunnu.recommender.examination.entity.Questions;
import cn.hunnu.recommender.examination.vo.QuestionOptionsVO;
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

    Page<Questions> queryQuestion(Page<Object> objectPage, Integer bankId);

}
