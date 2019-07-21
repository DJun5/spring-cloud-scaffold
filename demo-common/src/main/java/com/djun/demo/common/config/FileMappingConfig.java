package com.djun.demo.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileMappingConfig implements WebMvcConfigurer {
    //配置本地文件映射到url上
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 修改tomcat 虚拟映射
        // 定义图片存放路径
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+"C:/img"+"/");
    }
}
