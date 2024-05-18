package com.blog.gfblog.common;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



/**
 * minio 配置类
 *
 * @author husir
 * @date 2024/05/11
 */
@Data
@Component
public class MinIoClientConfig {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }
}
