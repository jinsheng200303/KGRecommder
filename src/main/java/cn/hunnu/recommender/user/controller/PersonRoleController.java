package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.entity.PersonRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "用户角色关联列表",notes = "用户角色关联列表")
    @GetMapping("/list")
    public List<PersonRole> list() {
        return personRoleService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/add-save")
    @ApiOperation(value = "用户角色关联的新增/修改",notes = "用户角色关联的新增/修改")
    public Result save(@Validated @RequestBody PersonRole personRole){
        personRoleService.saveOrUpdate(personRole);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("delete-id")
    @ApiOperation(value = "删除用户角色关联信息",notes = "删除用户角色关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        personRoleService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page-search")
    @ApiOperation(value = "用户角色关联信息查询",notes = "用户角色关联信息查询")
    public Result queryPersonInfo(@RequestBody PersonRoleQuery personRoleQuery){


        LambdaQueryWrapper<PersonRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PersonRole::getPersonRoleId);

        if(!"".equals(personRoleQuery.getUserID())&& personRoleQuery.getUserID()!=null){
            wrapper.like(PersonRole::getUserId, personRoleQuery.getUserID());
        }

        if(!"".equals(personRoleQuery.getRoleID())&& personRoleQuery.getRoleID()!=null){
            wrapper.like(PersonRole::getRoleId, personRoleQuery.getRoleID());
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



}
