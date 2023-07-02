package cn.hunnu.recommender.course.controller;

import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.course.dto.ClassesQuery;
import cn.hunnu.recommender.course.entity.ClassAnnouncement;
import cn.hunnu.recommender.course.entity.ClassUser;
import cn.hunnu.recommender.course.entity.Classes;
import cn.hunnu.recommender.course.mapper.ClassesMapper;
import cn.hunnu.recommender.course.service.ClassesService;
import cn.hunnu.recommender.user.entity.Person;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * <p>
 * 课堂 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-28
 */
@RestController
@RequestMapping("/classes")
@Api(value = "课堂模块",tags = "课堂模块")
public class ClassesController extends CourseBaseController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private ClassesMapper classesMapper;

    @ApiOperation(value = "课堂列表",notes = "课堂列表")
    @GetMapping("/list")
    public List<Classes> list() {
        return classesService.list();
    }

    @PostMapping("/save")
    @ApiOperation(value = "课堂新增/修改",notes = "课堂新增/修改")
    public Result save(@Validated Classes classes,@RequestParam int id,
                       @RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }
        String url;
        // 上传文件到磁盘
        file.transferTo(uploadFile);
        // 数据库若不存在重复文件，则不删除刚才上传的文件
        url = "http://localhost:8080/classes/" + fileUUID;
        classes.setClassPicture(url);

        classesService.saveOrUpdate(classes);
        ClassUser classUser = new ClassUser();
        classUser.setClassId(classes.getClassId());
        classUser.setUserId(id);
        classUserService.saveOrUpdate(classUser);
        return Result.success(classes);
    }

    /**
     * 文件下载接口   http://localhost:9090/file/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }


    //根据ID删除课堂
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除课堂",notes = "删除课堂")
    public Result delete(@RequestBody List<Integer> IDS){
        classesService.removeByIds(IDS);
        return Result.success();
    }

    //分页模糊查询
    @PostMapping("/page")
    @ApiOperation(value = "课堂查询",notes = "课堂查询")
    public Result<Page<Classes>> queryClasses(@RequestBody ClassesQuery classesQuery){


        LambdaQueryWrapper<Classes> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Classes::getClassId);

        if(!"".equals(classesQuery.getClassName())&& classesQuery.getClassName()!=null){
            wrapper.like(Classes::getClassName, classesQuery.getClassName());
        }

        Page<Classes> page = classesService.page(
                new Page<>(
                        classesQuery.getPageNum(),
                        classesQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }
}
