package com.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.blog.gfblog.mapper")
public class GfblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(GfblogApplication.class, args);
        System.out.println("博客后台启动成功！可以开始你的操作！");
    }
}
