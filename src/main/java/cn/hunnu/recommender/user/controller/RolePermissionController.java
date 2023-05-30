package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.dto.RolePermissionQuery;
import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.entity.Role;
import cn.hunnu.recommender.user.entity.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/role-permission")
@Api(value = "角色权限关联模块",tags = "角色权限关联模块")
public class RolePermissionController extends userBaseController {
    @ApiOperation(value = "角色权限关联列表",notes = "角色权限关联列表")
    @GetMapping("/list")
    public List<RolePermission> list() {
        return rolePermissionService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/save")
    @ApiOperation(value = "角色权限关联的新增/修改",notes = "角色权限关联的新增/修改")
    public Result save(@Validated @RequestBody RolePermission rolePermission){
        rolePermissionService.saveOrUpdate(rolePermission);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除角色权限关联信息",notes = "删除角色权限关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        rolePermissionService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "角色权限关联信息查询",notes = "角色权限关联信息查询")
    public Result<Page<RolePermission>> queryPersonInfo(@RequestBody RolePermissionQuery rolePermissionQuery){


        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(RolePermission::getRolePermissionId);

        if(!"".equals(rolePermissionQuery.getPermissionID())&& rolePermissionQuery.getPermissionID()!=null){
            wrapper.like(RolePermission::getPermissionId, rolePermissionQuery.getPermissionID());
        }

        if(!"".equals(rolePermissionQuery.getRoleID())&& rolePermissionQuery.getRoleID()!=null){
            wrapper.like(RolePermission::getRoleId, rolePermissionQuery.getRoleID());
        }

        Page<RolePermission> page = rolePermissionService.page(
                new Page<>(
                        rolePermissionQuery.getPageNum(),
                        rolePermissionQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
