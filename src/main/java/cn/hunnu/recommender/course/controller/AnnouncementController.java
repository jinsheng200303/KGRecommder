package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.AnnouncementQuery;
import cn.hunnu.recommender.course.entity.Announcement;
import cn.hunnu.recommender.course.service.AnnouncementService;
import cn.hunnu.recommender.examination.entity.Papers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 公告 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-26
 */
@RestController
@RequestMapping("/announcement")
@Api(value = "公告模块",tags = "公告模块")
public class AnnouncementController extends CourseBaseController {

    @ApiOperation(value = "公告列表",notes = "公告列表")
    @GetMapping("/list")
    public List<Announcement> list() {
        return announcementService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "公告新增/修改",notes = "公告新增/修改")
    public Result save(@Validated @RequestBody Announcement announcement){
        announcementService.saveOrUpdate(announcement);
        return Result.success();
    }

    //根据ID删除公告
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除公告",notes = "删除公告")
    public Result delete(@RequestBody List<Integer> IDS){
        announcementService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "公告查询",notes = "公告查询")
    public Result<Page<Announcement>> queryAnnouncement(@RequestBody AnnouncementQuery announcementQuery){


        LambdaQueryWrapper<Announcement> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Announcement::getAnnouncementId);

        if(!"".equals(announcementQuery.getTitle())&& announcementQuery.getTitle()!=null){
            wrapper.like(Announcement::getTitle, announcementQuery.getTitle());
        }

        Page<Announcement> page = announcementService.page(
                new Page<>(
                        announcementQuery.getPageNum(),
                        announcementQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
