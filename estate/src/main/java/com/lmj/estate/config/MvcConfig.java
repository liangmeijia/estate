package com.lmj.estate.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 指定允许跨域的路径
                .allowedOrigins("*")// 允许来自此源的请求
                .allowedHeaders("*")// 允许的头信息
                .allowedMethods("*")// 允许的HTTP方法
                .maxAge(5000)// 预检请求的有效期
                .exposedHeaders("Content-Disposition"); // 暴露 Content-Disposition 头
    }
}
