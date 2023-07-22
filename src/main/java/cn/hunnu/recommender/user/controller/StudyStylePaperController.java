package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.StudyStylePaperQuery;
import cn.hunnu.recommender.user.entity.StudyStylePaper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户风格测试试卷 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/study-style-paper")
@Api(value = "用户风格测试试卷模块",tags = "用户风格测试试卷模块")
public class StudyStylePaperController extends userBaseController {

    @ApiOperation(value = "用户风格测试试卷列表",notes = "用户风格测试试卷列表")
    @GetMapping("/list")
    public List<StudyStylePaper> list() {
        return studyStylePaperService.list();
    }
    @PostMapping("/save")
    @ApiOperation(value = "用户风格测试试卷新增/修改",notes = "用户风格测试试卷新增/修改")
    public Result save(@Validated @RequestBody StudyStylePaper studyStyle){
        studyStylePaperService.saveOrUpdate(studyStyle);
        return Result.success();
    }

    //根据ID删除用户风格测试试卷
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除用户风格测试试卷",notes = "删除用户风格测试试卷")
    public Result delete(@RequestBody List<Integer> IDS){
        studyStylePaperService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "用户风格测试试卷信息查询",notes = "用户风格测试试卷信息查询")
    public Result<Page<StudyStylePaper>> queryStudyStyleInfo(@RequestBody StudyStylePaperQuery studyStyleQuery){


        LambdaQueryWrapper<StudyStylePaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(StudyStylePaper::getId);

        if(!"".equals(studyStyleQuery.getId())&& studyStyleQuery.getId()!=null){
            wrapper.eq(StudyStylePaper::getId, studyStyleQuery.getId());
        }

        Page<StudyStylePaper> page = studyStylePaperService.page(
                new Page<>(
                        studyStyleQuery.getPageNum(),
                        studyStyleQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

}
