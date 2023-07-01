package cn.hunnu.recommender.user.serviceImpl;

import cn.hunnu.recommender.exception.CustomException;
import cn.hunnu.recommender.user.dto.UserLoginDTO;
import cn.hunnu.recommender.user.dto.UserRegisterDTO;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.entity.Validation;
import cn.hunnu.recommender.user.mapper.PersonMapper;
import cn.hunnu.recommender.user.service.PersonService;
import cn.hunnu.recommender.user.service.ValidationService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.util.Date;

/**
 * <p>
 * 个人信息 服务实现类
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private ValidationService validationService;

    @Override
    public UserRegisterDTO register(UserRegisterDTO userRegisterDTO) {
        userRegisterDTO.getEmail();
        userRegisterDTO.getCode();
        return null;
    }

    @Override
    public void sendEmailCode(String email) {
        Date now = new Date();
        String code = RandomUtil.randomNumbers(4);
        //先查询验证码是否有效
        QueryWrapper<Validation> validationQueryWrapper = new QueryWrapper<>();
        validationQueryWrapper.eq("email",email);
        validationQueryWrapper.gt("time",now);  //查询数据库没过期的code
        Validation validation = validationService.getOne(validationQueryWrapper);
        if(validation != null){
            throw new CustomException(-1,"您当前的验证码仍然有效");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【hnsd】注册邮箱验证");
        message.setText("您本次邮箱注册的验证码是：" + code + "，有效期五分钟，请妥善保管，切勿泄露。");
        message.setSentDate(now);
        message.setFrom(from);
        message.setTo(email);
        javaMailSender.send(message);

        //发送成功之后把验证码存入数据库
        validationService.saveCode(email, code, DateUtil.offsetMinute(now,5));
    }
}
