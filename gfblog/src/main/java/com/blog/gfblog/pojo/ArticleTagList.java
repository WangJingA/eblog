package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 文章标签文章列表表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class ArticleTagList extends Model<ArticleTagList> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章到标签id
     */
    @TableId(value = "article_tag_list_id", type = IdType.AUTO)
    private String articleTagListId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章tag-id
     */
    private String articleTagId;


    public String getArticleTagListId() {
        return articleTagListId;
    }

    public void setArticleTagListId(String articleTagListId) {
        this.articleTagListId = articleTagListId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(String articleTagId) {
        this.articleTagId = articleTagId;
    }

    @Override
    protected Serializable pkVal() {
        return this.articleTagListId;
    }

    @Override
    public String toString() {
        return "ArticleTagList{" +
        "articleTagListId=" + articleTagListId +
        ", articleId=" + articleId +
        ", articleTagId=" + articleTagId +
        "}";
    }
}
