package com.blog.gfblog.entity.dto.article;


import com.blog.gfblog.pojo.Article;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 文章管理界面列表数据
 * @author husir
 * @date 2024/06/08
 */
@Data
public class ArticleMainDataDTO {
    /**
     * 文章uuid
     */
    private String articleId;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 文字分类id
     */
    private String articleTypeId;


    /**
     * 文章类型名称
     */
    private String articleTypeName;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章添加时间
     */
    private LocalDateTime articleAddTime;

    /**
     * 文章内容
     */
    private String articleContext;

    /**
     * 点赞次数
     */
    private Integer articleGoodNumber;

    /**
     * 收藏次数
     */
    private Integer articleCollectionNumber;
}
