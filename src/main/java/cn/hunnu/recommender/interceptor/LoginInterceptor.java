package cn.hunnu.recommender.interceptor;

import cn.hunnu.recommender.exception.CustomException;
import cn.hunnu.recommender.user.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//登陆拦截器
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印请求信息
        log.info("------------- LoginInterceptor 开始 -------------");
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        // 判断Content-Type是否为multipart/form-data
        String contentType = request.getHeader("Content-Type");
        if (contentType != null && contentType.startsWith("multipart/form-data")) {
            return true;
        }

        String path = request.getRequestURL().toString();
        log.info("接口登录拦截：，path：{}", path);

        //获取header的token参数
        String token = request.getHeader("Authorization");
        log.info("登录校验开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            log.info( "token为空，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return false;
        }

        Claims claims = JwtUtils.verifyJwt(token);
        //获取用户ID
        if (claims == null) {
            log.warn( "token无效，请求被拦截" );
            throw new CustomException(401,"token无效，请求被拦截");
        } else {
            Integer userId = (Integer)claims.get("userId");
            log.info("已登录：{}", userId);

            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("requestStartTime");
        log.info("------------- LoginInterceptor 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("LogInterceptor 结束");
    }
}