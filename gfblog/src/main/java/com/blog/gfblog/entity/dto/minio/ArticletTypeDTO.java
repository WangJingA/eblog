package com.blog.gfblog.entity.dto.minio;

import com.blog.gfblog.pojo.ArticleType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章类型返回类
 * @author husir
 * @date 2024/05/26
 */
@Data
@Accessors(chain = true)
public class ArticletTypeDTO extends ArticleType {
    /**
     * 文章数量
     */
    private String articleNumber;
}
