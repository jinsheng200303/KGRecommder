package cn.hunnu.recommender.course.service;

import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.vo.ClassInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课堂 服务类
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
public interface ClassesService extends IService<Classes> {

    Page<ClassInfoVO> classInfoQuery(Page<ClassInfoVO> page, String className, String userName, String categoryName);

    Integer findUserRole(Integer userId);

    void addClass(Integer userId, String className, String classCategoryId);
}
