package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.exception.CustomException;
import cn.hunnu.recommender.user.dto.PersonQuery;
import cn.hunnu.recommender.user.dto.UserLoginDTO;
import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.entity.Validation;
import cn.hunnu.recommender.user.service.PersonService;
import cn.hunnu.recommender.user.utils.JwtUtils;
import cn.hunnu.recommender.user.vo.UserVO;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 个人信息 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/person")
@Api(value = "用户信息模块",tags = "用户信息模块")
public class PersonController extends userBaseController {

    @ApiOperation(value = "用户列表",notes = "用户列表")
    @GetMapping("/list")
    public List<Person> list() {
        return personService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/save")
    @ApiOperation(value = "用户新增/修改",notes = "用户新增/修改")
    public Result save(@Validated @RequestBody Person person){
        personService.saveOrUpdate(person);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除用户",notes = "删除用户")
    public Result delete(@RequestBody List<Integer> IDS){
        personService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "用户信息查询",notes = "用户信息查询")
    public Result<Page<Person>> queryPersonInfo(@RequestBody PersonQuery personQuery){


        LambdaQueryWrapper<Person> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Person::getUserId);

        if(!"".equals(personQuery.getUsername())&& personQuery.getUsername()!=null){
            wrapper.like(Person::getUserName, personQuery.getUsername());
        }

        Page<Person> page = personService.page(
                new Page<>(
                        personQuery.getPageNum(),
                        personQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆",notes = "用户登陆")
    public Result login(@Validated @RequestBody UserLoginDTO userLoginDTO){
        LambdaQueryWrapper<Person> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Person::getEmail, userLoginDTO.getEmail())
                .eq(Person::getPassword, userLoginDTO.getPassword())
                .last("limit 1");
        Person userInfo = personService.getOne(wrapper);

        if (userInfo != null){
            //生成jwt
            String token = JwtUtils.generateToken(userInfo);
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("userName", userInfo.getUserName());
            map.put("phoneNumber", userInfo.getPhoneNumber());
            map.put("name", userInfo.getName());
            return Result.success(map);
        }else {
            //Result.error
            //return Result.error("请检查用户名密码是否正确");
            throw new CustomException("请检查用户名密码是否正确");
        }
    }

    //通过用户id查询出用户相关信息用于前端显示
    @GetMapping("/getById")
    @ApiOperation(value = "根据用户ID查找",notes = "根据用户ID查找")
    public Result getById(@RequestParam("userId") Integer userId){
        Person byId = personService.getById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(byId, userVO);
        return Result.success(userVO);
    }

    @PostMapping("/registerCode")
    @ApiOperation(value = "用户注册获取验证码",notes = "用户注册获取验证码")
    public Result registerCode(@Validated @RequestBody UserLoginDTO userLoginDTO){
        String username = userLoginDTO.getUserName();
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Validation::getEmail, email);
        Validation validationInfo = validationService.getOne(wrapper);
        if(StrUtil.isBlank(email) || StrUtil.isBlank(password) || StrUtil.isBlank(username)){
            return Result.error(400);
        }
        if(!StrUtil.isBlank(email) && !validationInfo.getEmail().equals(email)){
            personService.sendEmailCode(email);
        }
        return Result.success(null,"验证码发送成功！");
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public Result register(@Validated @RequestBody UserLoginDTO userLoginDTO){
        String username = userLoginDTO.getUserName();
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        String code = userLoginDTO.getCode();
        if(StrUtil.isBlank(email) || StrUtil.isBlank(code) || StrUtil.isBlank(password) || StrUtil.isBlank(username)){
            return Result.error(400);
        }
        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Validation::getEmail, email)
                .eq(Validation::getCode, code);
        Validation validationInfo = validationService.getOne(wrapper);

        LambdaQueryWrapper<Person> wrapperPerson = new LambdaQueryWrapper<>();
        wrapperPerson.eq(Person::getUserName, userLoginDTO.getUserName())
                .eq(Person::getPassword, userLoginDTO.getPassword())
                .eq(Person::getEmail, userLoginDTO.getEmail());
        Person userInfo = personService.getOne(wrapperPerson);

        if (validationInfo != null && userInfo == null){
            Person person = new Person();
            person.setUserName(username);
            person.setEmail(email);
            person.setPassword(password);
            save(person);
            return Result.success(null,"用户注册成功");
        }else {
            //Result.error
            //return Result.error("请检查用户名密码是否正确");
            throw new CustomException("用户注册失败");
        }
    }

}
