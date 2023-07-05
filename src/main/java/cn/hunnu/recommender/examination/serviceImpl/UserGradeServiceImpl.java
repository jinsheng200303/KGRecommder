package cn.hunnu.recommender.examination.serviceImpl;

import cn.hunnu.recommender.examination.entity.UserGrade;
import cn.hunnu.recommender.examination.mapper.UserGradeMapper;
import cn.hunnu.recommender.examination.service.UserGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生成绩表 服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-05
 */
@Service
public class UserGradeServiceImpl extends ServiceImpl<UserGradeMapper, UserGrade> implements UserGradeService {

}
