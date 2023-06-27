package cn.hunnu.recommender.user.service;

import cn.hunnu.recommender.user.entity.Validation;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 邮箱验证表 服务类
 * </p>
 *
 * @author czj
 * @since 2023-06-27
 */
public interface ValidationService extends IService<Validation> {

    void saveCode(String email, String code, DateTime dateTime);
}
