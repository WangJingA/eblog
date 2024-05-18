package com.blog.gfblog.common;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 文档配置类
 * @author husir
 * @date 2024/05/18
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class SwaggerConfig {
    @Bean(value = "blog_api_doc")
    public Docket blogApiDoc(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("易博客API文档")
                        .version("v1.0.0")
                        .contact(new Contact("eblog", "http://121.37.28.93:8888", "1670373895@qq.com"))
                        .description("易博客API文档")
                        .build()
                )
                //分组名称
                .groupName("Swagger在线文档")
                // select()：生成 API 文档的选择器，用于指定要生成哪些 API 文档
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.blog.gfblog.controller"))
                // paths()：指定要生成哪个 URL 匹配模式下的 API 文档。这里使用 PathSelectors.any()，表示生成所有的 API 文档。
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
