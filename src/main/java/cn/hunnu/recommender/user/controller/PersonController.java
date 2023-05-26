package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.exception.CustomException;
import cn.hunnu.recommender.user.dto.PersonQuery;
import cn.hunnu.recommender.user.dto.UserLoginDTO;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.utils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result queryPersonInfo(@RequestBody PersonQuery personQuery){


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
        wrapper.eq(Person::getUserName, userLoginDTO.getUserName())
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


}
