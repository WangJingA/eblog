package com.blog.gfblog.entity.dto.minio;

import lombok.Data;

@Data
public class MinioResponseDTO {

    private String objectName;

    private Long size;
}
