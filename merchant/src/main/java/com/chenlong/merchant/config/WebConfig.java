package com.chenlong.merchant.config;

import com.chenlong.merchant.interceptor.LoginInterceptor;
import com.chenlong.merchant.resolver.ArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

import java.util.List;

//配置SpringMVC,发现参数,调用ArgumentResolverResolver,并注入的,配置类
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(new ServletWebArgumentResolverAdapter(new ArgumentResolver()));
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:static/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:static/img/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:static/fonts/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:static/favicon.ico");
        super.addResourceHandlers(registry);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) // 权限拦截器
                .addPathPatterns("/**") // 拦截所有
                .excludePathPatterns("/") // 排除拦截默认
                .excludePathPatterns("/login") // 排除拦截默认
                .excludePathPatterns("/login.do")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/favicon.ico"); // 排除拦截静态资源
        super.addInterceptors(registry);
    }

}
