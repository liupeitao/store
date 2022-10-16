package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
// 加载当前拦截器配置
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    //自定义拦截器对象
    private HandlerInterceptor handlerInterceptor = new LoginInterceptor();
    private List<String> patterns = new ArrayList<>();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        setPatterns();
        registry.addInterceptor(handlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }

    public  void setPatterns(){
        patterns.add("/bootstrap3/**");
        //patterns.add("/css/**");
        patterns.add("/web/index.html");
        patterns.add("/web/login.html");
        patterns.add("web/register.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
    }
}
