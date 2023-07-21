package cn.hunnu.recommender.examination.serviceImpl;

import cn.hunnu.recommender.examination.entity.StudentPaper;
import cn.hunnu.recommender.examination.mapper.StudentPaperMapper;
import cn.hunnu.recommender.examination.service.StudentPaperService;
import cn.hunnu.recommender.user.entity.PersonKnowledge;
import cn.hunnu.recommender.user.entity.RolePermission;
import cn.hunnu.recommender.user.mapper.PersonKnowledgeMapper;
import cn.hunnu.recommender.user.mapper.RolePermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-11
 */
@Service
public class StudentPaperServiceImpl extends ServiceImpl<StudentPaperMapper, StudentPaper> implements StudentPaperService {
    @Autowired
    PersonKnowledgeMapper personKnowledgeMapper;

    @Transactional
    @Override
    public void revisePersonKnowledge(Integer userId,List<Integer> listKnowledgeId, List<Float> listPermission) {
        //再把前端传过来的权限ID绑定到当前的角色ID上去
        for (int i = 0; i < listKnowledgeId.size(); i++) {
            PersonKnowledge personKnowledge = new PersonKnowledge();
            personKnowledge.setUserId(userId);
            personKnowledge.setKnowledgeId(listKnowledgeId.get(i));
            personKnowledge.setComprehension(listPermission.get(i));
            personKnowledgeMapper.insert(personKnowledge);
        }
    }
}
