package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 文章类型表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class ArticleType extends Model<ArticleType> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件类型表id
     */
    @TableId(value = "article_type_id", type = IdType.AUTO)
    private String articleTypeId;

    /**
     * 文章类型名称
     */
    private String articleTypeName;

    /**
     * 文章类型排序
     */
    private Integer articleTypeOrder;

    /**
     * 文章类型图片地址
     */
    private String articleTypeImageUrl;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    public String getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(String articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public String getArticleTypeName() {
        return articleTypeName;
    }

    public void setArticleTypeName(String articleTypeName) {
        this.articleTypeName = articleTypeName;
    }

    public Integer getArticleTypeOrder() {
        return articleTypeOrder;
    }

    public void setArticleTypeOrder(Integer articleTypeOrder) {
        this.articleTypeOrder = articleTypeOrder;
    }

    public String getArticleTypeImageUrl() {
        return articleTypeImageUrl;
    }

    public void setArticleTypeImageUrl(String articleTypeImageUrl) {
        this.articleTypeImageUrl = articleTypeImageUrl;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.articleTypeId;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
        "articleTypeId=" + articleTypeId +
        ", articleTypeName=" + articleTypeName +
        ", articleTypeOrder=" + articleTypeOrder +
        ", articleTypeImageUrl=" + articleTypeImageUrl +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
