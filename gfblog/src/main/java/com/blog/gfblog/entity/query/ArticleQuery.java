package com.blog.gfblog.entity.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 文章管理查询类
 * @author husir
 * @date 2024/05/26
 */
@Data
@ApiModel
public class ArticleQuery {
    /**
     * 文章标题
     */
    private String articleTitle;
}
