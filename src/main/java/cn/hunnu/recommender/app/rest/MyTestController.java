package cn.hunnu.recommender.app.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //将数据以json形式返回
public class MyTestController {

    @GetMapping("/test")
    public String test(){
        return "Hello World!!78!";
    }
}
