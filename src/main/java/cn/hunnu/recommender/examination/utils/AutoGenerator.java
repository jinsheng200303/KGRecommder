package cn.hunnu.recommender.examination.utils;


import cn.hunnu.recommender.examination.controller.examinationBaseController;
import cn.hunnu.recommender.examination.entity.examinationBaseEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoGenerator {

    private static final String URL = "jdbc:mysql://db.zhangxuxiang.cn:13306/recommender?useUnicode=true&useSSL=false&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Zxx123456";
    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();

        //tables.add("exams");
//        tables.add("papers");
//        tables.add("question_bank");
        tables.add("questions");


        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("JinSheng")               //作者
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("cn.hunnu.recommender.examination")
                            //留空请求路径中就会少一个层级
                            .moduleName("")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
//                           .addTablePrefix("p_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            //如果没有共有属性字段父类请注释这行
                            .superClass(examinationBaseEntity.class)
                            //开启Lombok，默认生成@Get，@Set，可以手动换成@Data
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.UPDATE))
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            //如果没有父类请注释掉
                            .superClass(examinationBaseController.class)
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