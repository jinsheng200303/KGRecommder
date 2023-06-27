package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.user.entity.Validation;
import cn.hunnu.recommender.user.mapper.ValidationMapper;
import cn.hunnu.recommender.user.service.ValidationService;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮箱验证表 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-06-27
 */
@Service
public class ValidationServiceImpl extends ServiceImpl<ValidationMapper, Validation> implements ValidationService {

    @Override
    public void saveCode(String email, String code, DateTime dateTime) {
        Validation validation = new Validation();
        validation.setCode(code);
        validation.setEmail(email);
        validation.setTime(dateTime);

        //删除验证
        UpdateWrapper<Validation> validationUpdateWrapper = new UpdateWrapper<>();
        validationUpdateWrapper.eq("email",email);
        remove(validationUpdateWrapper);

        save(validation);
    }
}
