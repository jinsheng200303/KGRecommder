package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.mapper.ClassesMapper;
import cn.hunnu.recommender.course.service.ClassesService;
import cn.hunnu.recommender.course.vo.ClassInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课堂 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

    @Autowired
    ClassesMapper classesMapper;
    @Override
    public Page<ClassInfoVO> classInfoQuery(Page<ClassInfoVO> page, String className, String userName, String categoryName) {
        return classesMapper.classInfoQuery(page,className,userName,categoryName);
    }

    @Override
    public Integer findUserRole(Integer userId) {
        return classesMapper.findUsersRole(userId);
    }

    @Override
    public void addClass(Integer userId, String className, String categoryId) {
        classesMapper.addClass(userId,className,categoryId);
    }
}
