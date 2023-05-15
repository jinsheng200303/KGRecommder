package cn.hunnu.recommender.mytest.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "开发测试模块",tags = "开发测试模块")
@RestController //将数据以json形式返回
public class MyTestController {

    @GetMapping("/test")
    @ApiOperation(value = "测试",notes = "测试")
    public String test(){
        return "Hello World123!!!";
    }
}
