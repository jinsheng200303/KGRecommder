package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.ClassCategory;
import cn.hunnu.recommender.course.mapper.ClassCategoryMapper;
import cn.hunnu.recommender.course.service.ClassCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课堂类别关联表 服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-08
 */
@Service
public class ClassCategoryServiceImpl extends ServiceImpl<ClassCategoryMapper, ClassCategory> implements ClassCategoryService {

}
