package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.examination.entity.ResourcesKnowledge;
import cn.hunnu.recommender.user.dto.PermissionQuery;
import cn.hunnu.recommender.user.entity.Permission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/Permission")
@Api(value = "权限模块",tags = "权限模块")
public class PermissionController extends userBaseController {

    @ApiOperation(value = "权限列表",notes = "权限列表")
    @GetMapping("/list")
    public List<Permission> list() {
        return permissionService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "权限新增/修改",notes = "权限新增/修改")
    public Result save(@Validated @RequestBody Permission permission){
        permissionService.saveOrUpdate(permission);
        return Result.success();
    }

    //根据ID删除权限
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除权限",notes = "删除权限")
    public Result delete(@RequestBody List<Integer> IDS){
        permissionService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "权限查询",notes = "权限查询")
    public Result<Page<Permission>> queryPermission(@RequestBody PermissionQuery permissionQuery){


        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Permission::getPermissionId);

        if(!"".equals(permissionQuery.getPermissionName())&& permissionQuery.getPermissionName()!=null){
            wrapper.like(Permission::getPermissionName, permissionQuery.getPermissionName());
        }

        Page<Permission> page = permissionService.page(
                new Page<>(
                        permissionQuery.getPageNum(),
                        permissionQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    @PostMapping("/findAllPermission")
    @ApiOperation(value = "分级查找权限",notes = "分级查找权限")
    public Result findAll(){
        //查出所有数据
        List<Permission> list = permissionService.list();
        //找出pid为null的一级权限
        List<Permission> parentNode = list.stream().filter(permission -> permission.getPid()==null).collect(Collectors.toList());
        //找出一级权限的子权限
        for(Permission permission : parentNode){
            //筛选所有数据中pid=父级id的数据就是二级权限
            permission.setChildren(list.stream().filter(m -> permission.getPermissionId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return Result.success(parentNode);
    }
}
