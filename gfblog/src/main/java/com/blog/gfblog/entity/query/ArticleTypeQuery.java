package com.blog.gfblog.entity.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class ArticleTypeQuery {
    /**
     * 文章类型名称
     */
    private String articleTypeCode;
    /**
     * 文章类型名称
     */
    private String articleTypeName;
    /**
     * 当前页
     */
    private Integer pageIndex;
    /**
     * 分页数量
     */
    private Integer pageSize;
}
