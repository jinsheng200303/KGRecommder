package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonStylePaperQuery;
import cn.hunnu.recommender.user.entity.PersonStylePaper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户风格测试数据存储 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/person-style-paper")
@Api(value = "用户风格测试数据存储模块",tags = "用户风格测试数据存储模块")
public class PersonStylePaperController extends userBaseController {

    @ApiOperation(value = "用户风格测试数据存储列表",notes = "用户风格测试数据存储列表")
    @GetMapping("/list")
    public List<PersonStylePaper> list() {
        return personStylePaperService.list();
    }
    @PostMapping("/save")
    @ApiOperation(value = "用户风格测试数据存储新增/修改",notes = "用户风格测试数据存储新增/修改")
    public Result save(@Validated @RequestBody PersonStylePaper personStylePaper){
        personStylePaperService.saveOrUpdate(personStylePaper);
        return Result.success();
    }

    //根据ID删除用户风格测试数据存储
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除用户风格测试数据存储",notes = "删除用户风格测试数据存储")
    public Result delete(@RequestBody List<Integer> IDS){
        personStylePaperService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "用户风格测试数据存储信息查询",notes = "用户风格测试数据存储信息查询")
    public Result<Page<PersonStylePaper>> queryPersonStylePaperInfo(@RequestBody PersonStylePaperQuery personStylePaperQuery){


        LambdaQueryWrapper<PersonStylePaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PersonStylePaper::getId);

        if(!"".equals(personStylePaperQuery.getUserId())&& personStylePaperQuery.getUserId()!=null){
            wrapper.eq(PersonStylePaper::getUserId, personStylePaperQuery.getUserId());
        }

        Page<PersonStylePaper> page = personStylePaperService.page(
                new Page<>(
                        personStylePaperQuery.getPageNum(),
                        personStylePaperQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

}
