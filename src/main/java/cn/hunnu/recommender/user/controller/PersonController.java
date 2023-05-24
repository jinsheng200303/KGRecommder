package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonQuery;
import cn.hunnu.recommender.user.entity.Person;
import cn.hunnu.recommender.user.serviceImpl.PersonServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "用户信息测试模块",tags = "用户信息测试模块")
public class PersonController extends userBaseController {

    @Autowired
    private PersonServiceImpl personService;

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/add-save")
    @ApiOperation(value = "用户新增/修改",notes = "用户新增/修改")
    public Result save(@Validated @RequestBody Person person){
        personService.saveOrUpdate(person);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("delete-id")
    @ApiOperation(value = "删除用户",notes = "删除用户")
    public Result delete(@RequestBody List<Integer> IDS){
        personService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page-search")
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



}
