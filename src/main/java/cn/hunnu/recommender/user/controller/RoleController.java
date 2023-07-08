package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.RoleQuery;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.entity.Role;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色模块",tags = "角色模块")
public class RoleController extends userBaseController {

    @ApiOperation(value = "角色列表",notes = "角色列表")
    @GetMapping("/list")
    public List<Role> list() {
        return roleService.list();
    }
    @PostMapping("/save")
    @ApiOperation(value = "角色新增/修改",notes = "角色新增/修改")
    public Result save(@Validated @RequestBody Role role){
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    //根据ID删除角色
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除角色",notes = "删除角色")
    public Result delete(@RequestBody List<Integer> IDS){
        roleService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "角色信息查询",notes = "角色信息查询")
    public Result<Page<Role>> queryRoleInfo(@RequestBody RoleQuery roleQuery){


        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Role::getRoleId);

        if(!"".equals(roleQuery.getRoleName())&& roleQuery.getRoleName()!=null){
            wrapper.eq(Role::getRoleName, roleQuery.getRoleName());
        }

        Page<Role> page = roleService.page(
                new Page<>(
                        roleQuery.getPageNum(),
                        roleQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

}
