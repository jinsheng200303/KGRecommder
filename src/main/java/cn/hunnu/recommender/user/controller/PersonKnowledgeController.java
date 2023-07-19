package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonKnowledgeQuery;
import cn.hunnu.recommender.user.entity.PersonKnowledge;
import cn.hunnu.recommender.user.mapper.PersonKnowledgeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 个人知识点掌握程度关联表 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/person-knowledge")
@Api(value = "用户知识点掌握程度关联模块",tags = "用户知识点掌握程度关联模块")
public class PersonKnowledgeController extends userBaseController {

    PersonKnowledgeMapper personKnowledgeMapper;
    @ApiOperation(value = "用户知识点掌握程度关联列表",notes = "用户知识点掌握程度关联列表")
    @GetMapping("/list")
    public List<PersonKnowledge> list() {
        return personKnowledgeService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/save")
    @ApiOperation(value = "用户知识点掌握程度关联的新增/修改",notes = "用户知识点掌握程度关联的新增/修改")
    public Result save(@Validated @RequestBody PersonKnowledge personKnowledge){
        personKnowledgeService.saveOrUpdate(personKnowledge);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除用户知识点掌握程度关联信息",notes = "删除用户知识点掌握程度关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        personKnowledgeService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "用户知识点掌握程度关联信息查询",notes = "用户知识点掌握程度关联信息查询")
    public Result<Page<PersonKnowledge>> queryPersonKnowledgeInfo(@RequestBody PersonKnowledgeQuery personKnowledgeQuery){

        LambdaQueryWrapper<PersonKnowledge> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(PersonKnowledge::getUserKnowledgeId);

        if(!"".equals(personKnowledgeQuery.getUserId())&& personKnowledgeQuery.getUserId()!=null){
            wrapper.eq(PersonKnowledge::getUserId, personKnowledgeQuery.getUserId());
        }

        Page<PersonKnowledge> page = personKnowledgeService.page(
                new Page<>(
                        personKnowledgeQuery.getPageNum(),
                        personKnowledgeQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
