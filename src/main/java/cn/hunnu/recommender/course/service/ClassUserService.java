package cn.hunnu.recommender.course.service;

import cn.hunnu.recommender.course.entity.ClassUser;
import cn.hunnu.recommender.course.entity.Classes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课堂用户 服务类
 * </p>
 *
 * @author czj
 * @since 2023-05-30
 */
public interface ClassUserService extends IService<ClassUser> {

    //根据用户ID查询其加入的课堂信息
    List<Classes> selectClass(Integer userId);
}
