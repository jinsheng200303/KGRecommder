package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.ValidationQuery;
import cn.hunnu.recommender.user.entity.Validation;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.hunnu.recommender.user.controller.userBaseController;

import java.util.List;

/**
 * <p>
 * 邮箱验证表 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-06-27
 */
@RestController
@RequestMapping("/validation")
@Api(value = "邮箱验证模块",tags = "邮箱验证模块")
public class ValidationController extends userBaseController {
    @ApiOperation(value = "邮箱验证列表",notes = "邮箱验证列表")
    @GetMapping("/list")
    public List<Validation> list() {
        return validationService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/save")
    @ApiOperation(value = "邮箱验证的新增/修改",notes = "邮箱验证的新增/修改")
    public Result save(@Validated @RequestBody Validation Validation){
        validationService.saveOrUpdate(Validation);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除邮箱验证信息",notes = "删除邮箱验证信息")
    public Result delete(@RequestBody List<Integer> IDS){
        validationService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "邮箱验证信息查询",notes = "邮箱验证信息查询")
    public Result<Page<Validation>> queryValidation(@RequestBody ValidationQuery ValidationQuery){


        LambdaQueryWrapper<Validation> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Validation::getId);

        if(!"".equals(ValidationQuery.getId())&& ValidationQuery.getId()!=null){
            wrapper.eq(Validation::getId, ValidationQuery.getId());
        }

        Page<Validation> page = validationService.page(
                new Page<>(
                        ValidationQuery.getPageNum(),
                        ValidationQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}

