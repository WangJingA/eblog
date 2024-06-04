package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章uuid
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文字分类id
     */
    private String articleTypeId;

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


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(String articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public LocalDateTime getArticleAddTime() {
        return articleAddTime;
    }

    public void setArticleAddTime(LocalDateTime articleAddTime) {
        this.articleAddTime = articleAddTime;
    }

    public String getArticleContext() {
        return articleContext;
    }

    public void setArticleContext(String articleContext) {
        this.articleContext = articleContext;
    }

    public Integer getArticleGoodNumber() {
        return articleGoodNumber;
    }

    public void setArticleGoodNumber(Integer articleGoodNumber) {
        this.articleGoodNumber = articleGoodNumber;
    }

    public Integer getArticleCollectionNumber() {
        return articleCollectionNumber;
    }

    public void setArticleCollectionNumber(Integer articleCollectionNumber) {
        this.articleCollectionNumber = articleCollectionNumber;
    }

    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }

    @Override
    public String toString() {
        return "Article{" +
        "articleId=" + articleId +
        ", userId=" + userId +
        ", articleTypeId=" + articleTypeId +
        ", articleTitle=" + articleTitle +
        ", articleAddTime=" + articleAddTime +
        ", articleContext=" + articleContext +
        ", articleGoodNumber=" + articleGoodNumber +
        ", articleCollectionNumber=" + articleCollectionNumber +
        "}";
    }
}
