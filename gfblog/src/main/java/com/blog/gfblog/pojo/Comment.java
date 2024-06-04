package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private String commentId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 评论时间
     */
    private LocalDateTime commentTime;

    /**
     * 点赞次数
     */
    private Integer commentGoodNumber;


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

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

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentGoodNumber() {
        return commentGoodNumber;
    }

    public void setCommentGoodNumber(Integer commentGoodNumber) {
        this.commentGoodNumber = commentGoodNumber;
    }

    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
        "commentId=" + commentId +
        ", articleId=" + articleId +
        ", userId=" + userId +
        ", commentTime=" + commentTime +
        ", commentGoodNumber=" + commentGoodNumber +
        "}";
    }
}
