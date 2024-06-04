package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 文章标签表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class ArticleTag extends Model<ArticleTag> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标签id
     */
    @TableId(value = "article_tag_id", type = IdType.AUTO)
    private String articleTagId;

    /**
     * 标签名称
     */
    private String articleTagName;

    /**
     * 添加标签时间
     */
    private LocalDateTime articleAddTagTime;

    /**
     * 标签修改时间
     */
    private LocalDateTime articleUpdateTime;


    public String getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(String articleTagId) {
        this.articleTagId = articleTagId;
    }

    public String getArticleTagName() {
        return articleTagName;
    }

    public void setArticleTagName(String articleTagName) {
        this.articleTagName = articleTagName;
    }

    public LocalDateTime getArticleAddTagTime() {
        return articleAddTagTime;
    }

    public void setArticleAddTagTime(LocalDateTime articleAddTagTime) {
        this.articleAddTagTime = articleAddTagTime;
    }

    public LocalDateTime getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(LocalDateTime articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.articleTagId;
    }

    @Override
    public String toString() {
        return "ArticleTag{" +
        "articleTagId=" + articleTagId +
        ", articleTagName=" + articleTagName +
        ", articleAddTagTime=" + articleAddTagTime +
        ", articleUpdateTime=" + articleUpdateTime +
        "}";
    }
}
