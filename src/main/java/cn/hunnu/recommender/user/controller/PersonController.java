package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.entity.Knowledge;
import cn.hunnu.recommender.course.service.ClassUserService;
import cn.hunnu.recommender.exception.CustomException;
import cn.hunnu.recommender.user.dto.*;
import cn.hunnu.recommender.user.entity.*;
import cn.hunnu.recommender.user.mapper.PersonMapper;
import cn.hunnu.recommender.user.service.PersonKnowledgeService;
import cn.hunnu.recommender.user.service.PersonService;
import cn.hunnu.recommender.user.utils.JwtUtils;
import cn.hunnu.recommender.user.vo.UserRoleVO;
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

    @Resource
    PersonRoleController personRoleController;
    @Resource
    PersonMapper personMapper;
    @Resource
    ClassUserService classUserService;
    @Resource
    PersonKnowledgeService personKnowledgeService;

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

    //根据用户ID数组查询用户
    @PostMapping("/UserList")
    @ApiOperation(value = "用户ID数组查询",notes = "用户ID数组查询")
    public Result UserList(@RequestBody List<Integer> IDS){
        return Result.success(personMapper.selectBatchIds(IDS));
    }

    @PostMapping("/page")
    @ApiOperation(value = "用户信息查询",notes = "用户信息查询")
    public Result<Page<Person>> queryPersonInfo(@RequestBody PersonQuery personQuery){

        LambdaQueryWrapper<Person> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Person::getUserId);

        if(!"".equals(personQuery.getUserId())&& personQuery.getUserId()!=null){
            wrapper.eq(Person::getUserId, personQuery.getUserId());
        }

        if(!"".equals(personQuery.getUsername())&& personQuery.getUsername()!=null){
            wrapper.like(Person::getUserName, personQuery.getUsername());
        }

        if(!"".equals(personQuery.getEmail())&& personQuery.getEmail()!=null){
            wrapper.like(Person::getEmail, personQuery.getEmail());
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

        LambdaQueryWrapper<PersonRole> wrapperPR = new LambdaQueryWrapper<>();
        wrapperPR.eq(PersonRole::getUserId, userInfo.getUserId())
                .last("limit 1");
        PersonRole personRole = personRoleService.getOne(wrapperPR);

        if (userInfo != null){
            //生成jwt
            String token = JwtUtils.generateToken(userInfo);
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("userName", userInfo.getUserName());
            map.put("roleId", personRole.getRoleId());
            map.put("avatar", userInfo.getAvatar());
            map.put("userId",userInfo.getUserId());
            return Result.success(map);
        }else {
            //Result.error
            return Result.error("请检查用户名密码是否正确");
//            throw new CustomException("请检查用户名密码是否正确");
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
    public Result registerCode(@Validated @RequestBody UserRegisterCodeDTO userRegisterCodeDTO){
        boolean flag = true;
        String email = userRegisterCodeDTO.getEmail();
        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Validation::getEmail, email);
        Validation validationInfo = validationService.getOne(wrapper);
        if(StrUtil.isBlank(email)){
            return Result.error(400);
        }else{
//        if(!StrUtil.isBlank(email) && validationInfo==null) {
            flag = personService.sendEmailCode(email);
        }
        if (flag) {
            return Result.success(null, "验证码发送成功！");
        } else {
            return Result.error(200, "重复发送，验证码5分钟内有效！");
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public Result register(@Validated @RequestBody UserRegisterDTO userRegisterDTO){
        Person person = new Person();
        BeanUtils.copyProperties(userRegisterDTO,person);
        if(StrUtil.isBlank(userRegisterDTO.getEmail())
                || StrUtil.isBlank(userRegisterDTO.getCode())
                || StrUtil.isBlank(userRegisterDTO.getPassword())
                || StrUtil.isBlank(userRegisterDTO.getUserName())){
            return Result.error(400);
        }
        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Validation::getEmail, userRegisterDTO.getEmail())
                .eq(Validation::getCode, userRegisterDTO.getCode());
        Validation validationInfo = validationService.getOne(wrapper);

        LambdaQueryWrapper<Person> wrapperPerson = new LambdaQueryWrapper<>();
        //邮箱是唯一匹配凭证，但为了测试更多数据加上用户名和密码匹配来降低匹配成功率
        wrapperPerson.eq(Person::getUserName, userRegisterDTO.getUserName())
                .eq(Person::getPassword, userRegisterDTO.getPassword())
                .eq(Person::getEmail, userRegisterDTO.getEmail());
        Person userInfo = personService.getOne(wrapperPerson);

        if (validationInfo != null && userInfo == null){
            save(person);

            LambdaQueryWrapper<Person> wrapperPerson2 = new LambdaQueryWrapper<>();
            //邮箱是唯一匹配凭证，但为了测试更多数据加上用户名和密码匹配来降低匹配成功率
            wrapperPerson2.eq(Person::getUserName, userRegisterDTO.getUserName())
                    .eq(Person::getPassword, userRegisterDTO.getPassword())
                    .eq(Person::getEmail, userRegisterDTO.getEmail());
            Person userInfo2 = personService.getOne(wrapperPerson);

            PersonRole personRole = new PersonRole();
            personRole.setRoleId(1);
            personRole.setUserId(userInfo2.getUserId());
            personRoleController.save(personRole);
            return Result.success(null,"用户注册成功");
        }else {
            throw new CustomException("用户注册失败");
        }
    }

    @PostMapping("/pageUserName")
    @ApiOperation(value = "用户名角色关联信息查询",notes = "用户名角色关联信息查询")
    public Result queryUserNameRoleInfo(@RequestBody UserRoleVO userRoleVO){

        Page<UserRoleVO> page = personService.getUserNameRole(new Page<>(userRoleVO.getPageNum(),userRoleVO.getPageSize())
                , userRoleVO.getUserName(),userRoleVO.getRoleName());
        return Result.success(page);
    }

    @ApiOperation(value = "根据用户ID查找其加入的课堂信息", notes = "根据用户ID查找其加入的课堂信息")
    @GetMapping("/view/{userId}")
    public Result viewClass(@PathVariable Integer userId){
        List<Classes> list = classUserService.selectClass(userId);
        return Result.success(list);
    }

    @ApiOperation(value = "根据用户ID查找其知识点掌握程度", notes = "根据用户ID查找其知识点掌握程度")
    @GetMapping("/dina/{userId}")
    public Result viewKnowledge(@PathVariable Integer userId){
        List<Knowledge> list = personKnowledgeService.selectKnowledge(userId);
        return Result.success(list);
    }

}
