package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.mapper.PermissionMapper;
import cn.hunnu.recommender.user.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.hunnu.recommender.user.controller.userBaseController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-19
 */
@RestController
@RequestMapping("/permission")
@Api(value = "测试模块",tags = "测试模块")
public class PermissionController extends userBaseController {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/userTest")
    @ApiOperation(value = "用户测试",notes = "用户测试")
    public String userTest(){
        return "okk";
    }

    @GetMapping("/permissionTable")
    @ApiOperation(value = "权限表",notes = "权限表")
    public List<Permission> permissionTable(){
        return permissionMapper.selectList(null);
    }

    @PostMapping("/tt")
    public Result tt(@RequestBody Permission permission){
        Map<String,Object> data = permissionService.tt(permission);
        if(data != null){
            return Result.success(data,"成功接受前端传值");
        }
        return Result.error(50000);
    }
}
