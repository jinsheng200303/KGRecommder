package cn.hunnu.recommender.course.utils;


import cn.hunnu.recommender.course.controller.CourseBaseController;
import cn.hunnu.recommender.course.entity.CourseBaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeGenerator {
    private static final String URL = "jdbc:mysql://db.zhangxuxiang.cn:13306/recommender?useUnicode=true&useSSL=false&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Zxx123456";
    public static void main(String[] args) {
        String mapperLocation = "D:\\neo4j\\zhang'sProject\\recommender\\src\\main\\resources\\mapper";
        List<String> tables = new ArrayList<>();

        tables.add("announcement");

        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("czj") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd")
                            .outputDir("D:\\neo4j\\zhang'sProject\\recommender\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.hunnu.recommender.course") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            //如果没有共有属性字段父类请注释这行
                            .superClass(CourseBaseEntity.class)
                            //开启Lombok，默认生成@Get，@Set，可以手动换成@Data
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.UPDATE))
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            //如果没有父类请注释掉
                            .superClass(CourseBaseController.class)
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
