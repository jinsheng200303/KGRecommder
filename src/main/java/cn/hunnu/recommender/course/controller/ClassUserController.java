package cn.hunnu.recommender.course.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassUserQuery;
import cn.hunnu.recommender.course.entity.ClassStudentGraph;
import cn.hunnu.recommender.course.entity.ClassUser;
import cn.hunnu.recommender.course.mapper.ClassUserMapper;
import cn.hunnu.recommender.course.service.ClassUserService;
import cn.hunnu.recommender.course.vo.ClassVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 课堂用户 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-30
 */
@RestController
@RequestMapping("/class-user")
@Api(value = "课堂用户关联模块",tags = "课堂用户关联模块")
public class ClassUserController extends CourseBaseController {
    @Autowired
    private ClassUserMapper classUserMapper;

    @ApiOperation(value = "课堂用户关联列表",notes = "课堂用户关联列表")
    @GetMapping("/list")
    public List<ClassUser> list() {
        return classUserService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂用户关联的新增/修改",notes = "课堂用户关联的新增/修改")
    public Result save(@Validated @RequestBody ClassUser classUser){
        classUserService.saveOrUpdate(classUser);
        return Result.success();
    }

    //根据ID删除课堂用户关联信息
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂用户关联信息",notes = "删除课堂用户关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        classUserService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂用户关联查询",notes = "课堂用户关联查询")
    public Result<Page<ClassUser>> queryClassUser(@RequestBody ClassUserQuery classUserQuery){


        LambdaQueryWrapper<ClassUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ClassUser::getClassId);

        if(!"".equals(classUserQuery.getUserID())&& classUserQuery.getUserID()!=null){
            wrapper.like(ClassUser::getUserId, classUserQuery.getUserID());
        }

        if(!"".equals(classUserQuery.getClassID())&& classUserQuery.getClassID()!=null){
            wrapper.like(ClassUser::getClassId, classUserQuery.getClassID());
        }

        Page<ClassUser> page = classUserService.page(
                new Page<>(
                        classUserQuery.getPageNum(),
                        classUserQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    @PostMapping("/class-student-query")
    @ApiOperation(value = "课堂学生查询",notes = "课堂学生查询")
    public List queryClassUser(@RequestParam int classId,@RequestParam int roleId){
        List<ClassVO> list =classUserMapper.classStudentQuery(classId,roleId);
        return list;
    }

    @PostMapping("/class-student-del")
    @ApiOperation(value = "删除指定课堂学生",notes = "删除指定课堂学生")
    public Result delClassUser(@RequestParam int classId,@RequestBody List<Integer> studentId){
        classUserMapper.classStudentDelete(classId,studentId);
        return Result.success();
    }
}
