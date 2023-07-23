package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.BrowseRecordsQuery;
import cn.hunnu.recommender.user.entity.BrowseRecords;
import cn.hunnu.recommender.user.mapper.BrowseRecordsMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 浏览记录表 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/browse-records")
@Api(value = "浏览记录模块",tags = "浏览记录模块")
public class BrowseRecordsController extends userBaseController {

    BrowseRecordsMapper browseRecordsMapper;
    @ApiOperation(value = "浏览记录列表",notes = "浏览记录列表")
    @GetMapping("/list")
    public List<BrowseRecords> list() {
        return browseRecordsService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/save")
    @ApiOperation(value = "浏览记录的新增/修改",notes = "浏览记录的新增/修改")
    public Result save(@Validated @RequestBody BrowseRecords browseRecords){
        browseRecordsService.saveOrUpdate(browseRecords);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除浏览记录信息",notes = "删除浏览记录信息")
    public Result delete(@RequestBody List<Integer> IDS){
        browseRecordsService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "浏览记录信息查询",notes = "浏览记录信息查询")
    public Result<Page<BrowseRecords>> queryBrowseRecordsInfo(@RequestBody BrowseRecordsQuery browseRecordsQuery){

        LambdaQueryWrapper<BrowseRecords> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(BrowseRecords::getBrowseRecordId);

        if(!"".equals(browseRecordsQuery.getUserId())&& browseRecordsQuery.getUserId()!=null){
            wrapper.eq(BrowseRecords::getUserId, browseRecordsQuery.getUserId());
        }

        if(!"".equals(browseRecordsQuery.getResourceId())&& browseRecordsQuery.getResourceId()!=null){
            wrapper.eq(BrowseRecords::getResourceId, browseRecordsQuery.getResourceId());
        }

        Page<BrowseRecords> page = browseRecordsService.page(
                new Page<>(
                        browseRecordsQuery.getPageNum(),
                        browseRecordsQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

}
