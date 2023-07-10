package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassAnnouncementQuery;
import cn.hunnu.recommender.course.entity.Announcement;
import cn.hunnu.recommender.course.entity.ClassAnnouncement;
import cn.hunnu.recommender.course.service.ClassAnnouncementService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 课堂公告 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/class-announcement")
@Api(value = "课堂公告关联模块",tags = "课堂公告关联模块")
public class ClassAnnouncementController extends CourseBaseController {
    @ApiOperation(value = "课堂公告关联列表",notes = "课堂公告关联列表")
    @GetMapping("/list")
    public List<ClassAnnouncement> list() {
        return classAnnouncementService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂公告关联的新增/修改",notes = "课堂公告关联的新增/修改")
    public Result save(@Validated @RequestBody ClassAnnouncement classAnnouncement){
        classAnnouncementService.saveOrUpdate(classAnnouncement);
        return Result.success();
    }

    //根据ID删除课堂公告关联信息
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂公告关联信息",notes = "删除课堂公告关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        classAnnouncementService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂公告关联查询",notes = "课堂公告关联查询")
    public Result<Page<ClassAnnouncement>> queryClassAnnouncement(@RequestBody ClassAnnouncementQuery classAnnouncementQuery){


        LambdaQueryWrapper<ClassAnnouncement> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ClassAnnouncement::getClassId);

        if(!"".equals(classAnnouncementQuery.getAnnouncementId())&& classAnnouncementQuery.getAnnouncementId()!=null){
            wrapper.like(ClassAnnouncement::getAnnouncementId, classAnnouncementQuery.getAnnouncementId());
        }

        if(!"".equals(classAnnouncementQuery.getClassId())&& classAnnouncementQuery.getClassId()!=null){
            wrapper.like(ClassAnnouncement::getClassId, classAnnouncementQuery.getClassId());
        }

        Page<ClassAnnouncement> page = classAnnouncementService.page(
                new Page<>(
                        classAnnouncementQuery.getPageNum(),
                        classAnnouncementQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
