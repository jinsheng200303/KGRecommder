package cn.hunnu.recommender.mytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
引入swagger生成api文档的swagger配置文件
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.hunnu.recommender.mytest.rest"))//找到对应rest包
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder().title("基于知识建模和认知诊断的大学生个性化智能辅学系统").version("v1.0").build());
    }

}