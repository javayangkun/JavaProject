package com.huaiwei.rbac.config;

import com.huaiwei.rbac.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableTransactionManagement
@Configuration
public class CommonWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor login = new LoginInterceptor();
        registry.addInterceptor(login)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/", "/login","/captcha");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");

    }
}
