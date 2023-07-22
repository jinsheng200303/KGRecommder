package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.StudyStyleQuery;
import cn.hunnu.recommender.user.entity.StudyStyle;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学习风格 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/study-style")
@Api(value = "学习风格模块",tags = "学习风格模块")
public class StudyStyleController extends userBaseController {

    @ApiOperation(value = "学习风格列表",notes = "学习风格列表")
    @GetMapping("/list")
    public List<StudyStyle> list() {
        return studyStyleService.list();
    }
    @PostMapping("/save")
    @ApiOperation(value = "学习风格新增/修改",notes = "学习风格新增/修改")
    public Result save(@Validated @RequestBody StudyStyle studyStyle){
        studyStyleService.saveOrUpdate(studyStyle);
        return Result.success();
    }

    //根据ID删除学习风格
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除学习风格",notes = "删除学习风格")
    public Result delete(@RequestBody List<Integer> IDS){
        studyStyleService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "学习风格信息查询",notes = "学习风格信息查询")
    public Result<Page<StudyStyle>> queryStudyStyleInfo(@RequestBody StudyStyleQuery studyStyleQuery){


        LambdaQueryWrapper<StudyStyle> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(StudyStyle::getStudyStyleId);

        if(!"".equals(studyStyleQuery.getStudyStyleId())&& studyStyleQuery.getStudyStyleId()!=null){
            wrapper.eq(StudyStyle::getStudyStyleId, studyStyleQuery.getStudyStyleId());
        }

        Page<StudyStyle> page = studyStyleService.page(
                new Page<>(
                        studyStyleQuery.getPageNum(),
                        studyStyleQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

}
