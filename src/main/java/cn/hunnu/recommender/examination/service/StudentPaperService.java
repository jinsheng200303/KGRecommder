package cn.hunnu.recommender.examination.service;

import cn.hunnu.recommender.examination.entity.StudentPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-11
 */
public interface StudentPaperService extends IService<StudentPaper> {

    @Transactional
    void revisePersonKnowledge(Integer userId,List<Integer> listKnowledgeId, List<Float> listPermission);
}
