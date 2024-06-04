package com.blog.gfblog.entity.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel
@Accessors(chain = true)
public class ArticleTagQuery {
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 当前页码
     */
    private Integer pageIndex;
    /**
     * 页数量大小
     */
    private Integer pageSize;
}
