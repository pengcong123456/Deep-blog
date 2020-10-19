package com.pcc.deepblog;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DeepBlogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DeepBlogApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        //参数为当前springboot启动类
        //构造新资源
        return builder.sources(Application.class);
    }
}
