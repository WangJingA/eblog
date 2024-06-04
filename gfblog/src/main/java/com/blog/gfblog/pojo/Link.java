package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 友情连接表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class Link extends Model<Link> {

    private static final long serialVersionUID = 1L;

    /**
     * 友情链接id
     */
    @TableId(value = "link_id", type = IdType.AUTO)
    private String linkId;

    /**
     * 友情链接标题
     */
    private String linkTitle;

    /**
     * 友情链接地址
     */
    private String linkUrl;

    /**
     * 友情连接图标
     */
    private String linkLogoUrl;

    /**
     * 友情连接添加时间
     */
    private LocalDateTime linkAddTime;


    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkLogoUrl() {
        return linkLogoUrl;
    }

    public void setLinkLogoUrl(String linkLogoUrl) {
        this.linkLogoUrl = linkLogoUrl;
    }

    public LocalDateTime getLinkAddTime() {
        return linkAddTime;
    }

    public void setLinkAddTime(LocalDateTime linkAddTime) {
        this.linkAddTime = linkAddTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.linkId;
    }

    @Override
    public String toString() {
        return "Link{" +
        "linkId=" + linkId +
        ", linkTitle=" + linkTitle +
        ", linkUrl=" + linkUrl +
        ", linkLogoUrl=" + linkLogoUrl +
        ", linkAddTime=" + linkAddTime +
        "}";
    }
}
