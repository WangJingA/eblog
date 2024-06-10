package com.blog.gfblog.entity.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    /**
     * 文章类型编码
     */
    private String articleTypeCode;
    /**
     * 页码
     */
    @NotNull(message = "当前页码不能为空")
    private Integer pageIndex;
    /**
     * 页大小
     */
    @NotNull(message = "页的大小不能为空")
    private Integer pageSize;
}
