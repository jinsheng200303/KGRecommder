package cn.hunnu.recommender.examination.rest;

import cn.hunnu.recommender.examination.dao.ExamsMapper;
import cn.hunnu.recommender.examination.entity.Exams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "开发测试模块",tags = "开发测试模块")
@RestController //将数据以json形式返回
public class MyTestController {

    @Value("${text}")
    private String text;

    @Autowired
    private ExamsMapper examsMapper;

    @GetMapping("/test")
    @ApiOperation(value = "测试",notes = "测试")
    public String test(){
        return "Hello World123!!!";
    }

    @GetMapping("/text")
    public String text(){
        return text;
    }

    @GetMapping("/exams")
    public List<Exams> exams(){
        return examsMapper.selectList(null);
    }
}
