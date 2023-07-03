package cn.hunnu.recommender.config;

import cn.hunnu.recommender.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


//拦截器配置
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                //允许直接访问的接口
                .excludePathPatterns(

                        "/person/login",


                        //TODO 开发暂时允许访问
                        "/person/list",
                        "/person/save",
                        "/person/getById",
                        "/person/page",
                        "/person/delBatch",
                        "/person/UserList",
                        "/announcement/*",
                        "/classes/*",
                        "/knowledge/*",
                        "/lesson/*",
                        "/class-announcement/*",
                        "/class-homework/*",
                        "/class-resource/*",
                        "/class-student-graph/*",
                        "/class-user/*",
                        "/teaching-material/*",
                        "/papers/**",
                        "/exams/**",
                        "/question-bank/**",
                        "/questions/**",
                        "/options/**",
                        "/records/**",
                        "/resources-knowledge/**",
                        "/resources/**",
                        "/diag-results/**",
                        "/record-diag/**",
                        "/homework/**",
                        "/validation/**",
                        "/person/registerCode",
                        "/person/register",
                        "/class-user/class-student",

                        "/doc.html",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security"

                );
    }


}