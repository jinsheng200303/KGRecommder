package cn.hunnu.recommender.graph.rest;

import cn.hunnu.recommender.graph.entity.BaseEntity;
import cn.hunnu.recommender.graph.service.IBaseService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api(value = "知识图谱模块",tags = "知识图谱模块")
@RestController
@RequestMapping(value = "/recommender/v1/graph/base", produces = "application/json")
public class BaseController {
    @Autowired
    IBaseService baseService;


    @GetMapping(value = "/getEntitiesById")
    public BaseEntity getEntitiesById(@RequestParam("id") String id) {
        log.info("根据id获取实体：{}", id);
        return baseService.getById(id);
    }

    @GetMapping(value = "/getEntitiesByName")
    public Object getEntitiesByName(@RequestParam("name") String name) {
        log.info("根据name获取实体：{}", name);
        return baseService.getByName(name);
    }
}
