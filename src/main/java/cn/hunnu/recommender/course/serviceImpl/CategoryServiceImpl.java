package cn.hunnu.recommender.course.serviceImpl;

import cn.hunnu.recommender.course.entity.Category;
import cn.hunnu.recommender.course.mapper.CategoryMapper;
import cn.hunnu.recommender.course.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JinSheng
 * @since 2023-07-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
