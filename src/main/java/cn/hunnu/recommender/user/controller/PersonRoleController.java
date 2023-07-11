package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.mapper.PersonRoleMapper;
import cn.hunnu.recommender.user.vo.UserRoleVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 个人角色关联表 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/person-role")
@Api(value = "用户角色关联模块",tags = "用户角色关联模块")
public class PersonRoleController extends userBaseController {

    PersonRoleMapper personRoleMapper;
    @ApiOperation(value = "用户角色关联列表",notes = "用户角色关联列表")
    @GetMapping("/list")
    public List<PersonRole> list() {
        return personRoleService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/save")
    @ApiOperation(value = "用户角色关联的新增/修改",notes = "用户角色关联的新增/修改")
    public Result save(@Validated @RequestBody PersonRole personRole){
        personRoleService.saveOrUpdate(personRole);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除用户角色关联信息",notes = "删除用户角色关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        personRoleService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "用户角色关联信息查询",notes = "用户角色关联信息查询")
    public Result<Page<PersonRole>> queryPersonRoleInfo(@RequestBody PersonRoleQuery personRoleQuery){

        LambdaQueryWrapper<PersonRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PersonRole::getPersonRoleId);

        if(!"".equals(personRoleQuery.getUserId())&& personRoleQuery.getUserId()!=null){
            wrapper.eq(PersonRole::getUserId, personRoleQuery.getUserId());
        }

        if(!"".equals(personRoleQuery.getRoleId())&& personRoleQuery.getRoleId()!=null){
            wrapper.eq(PersonRole::getRoleId, personRoleQuery.getRoleId());
        }

        Page<PersonRole> page = personRoleService.page(
                new Page<>(
                        personRoleQuery.getPageNum(),
                        personRoleQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    @PostMapping("/reviseUserRole")
    @ApiOperation(value = "根据用户ID修改用户角色",notes = "根据用户ID修改用户角色")
    public Result reviseUserRole(@Validated @RequestBody PersonRole personRole){
        personRoleService.reviseUserRole(personRole.getPersonRoleId(),personRole.getRoleId());
        return Result.success();
    }

    //根据用户名和roleId来新增用户角色
    @PostMapping("/addUserRole")
    @ApiOperation(value = "新增用户角色",notes = "新增用户角色")
    public Result addUserRole(@Validated @RequestBody PersonRole personRole){
        PersonRole personRole1 = new PersonRole();
        personRole1 = personRoleService.findUserRole(personRole.getUserId(),personRole.getRoleId());
        if (personRole1!=null){
            return Result.error("用户角色已经存在！");
        }else {
            int userId = -1;
            userId = personRoleService.findUserId(personRole.getUserId());
            if(userId==-1){
                return Result.error("用户不存在！");
            }else {
                personRoleService.addUserRole(personRole.getUserId(),personRole.getRoleId());
                return Result.success();
            }
        }
    }

    @PostMapping("/judgementUserRole")
    @ApiOperation(value = "判断用户角色是否存在",notes = "判断用户角色是否存在")
    public boolean judgementUserRole(@Validated @RequestBody PersonRole personRole){
        PersonRole personRole1 = new PersonRole();
        personRole1 = personRoleService.findUserRole(personRole.getUserId(),personRole.getRoleId());
        if (personRole1==null){
            return false;
        }else {
            return true;
        }
    }

    @PostMapping("/findUserRole")
    @ApiOperation(value = "查找用户的角色",notes = "查找用户的角色")
    public int findUsersRole(@RequestParam Integer userId){
        Integer roleId = personRoleService.findUsersRole(userId);
        if (roleId==null){
            return 0;
        }else {
            return roleId.intValue();
        }
    }
}
