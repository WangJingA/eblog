package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 广告表
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class Ad extends Model<Ad> {

    private static final long serialVersionUID = 1L;

    /**
     * 广告id
     */
    @TableId(value = "ad_id", type = IdType.AUTO)
    private String adId;

    /**
     * 广告标题
     */
    private String adTitle;

    /**
     * 广告的地址
     */
    private String adUrl;

    /**
     * 广告排序，升序排序
     */
    private Integer adSort;

    /**
     * 广告类型id
     */
    private String adTypeId;

    /**
     * 广告图片地址
     */
    private String adImageUrl;

    /**
     * 广告开始时间
     */
    private LocalDateTime adBeginTime;

    /**
     * 广告结束时间
     */
    private LocalDateTime adEndTime;

    /**
     * 广告添加时间
     */
    private LocalDateTime adAddTime;


    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public Integer getAdSort() {
        return adSort;
    }

    public void setAdSort(Integer adSort) {
        this.adSort = adSort;
    }

    public String getAdTypeId() {
        return adTypeId;
    }

    public void setAdTypeId(String adTypeId) {
        this.adTypeId = adTypeId;
    }

    public String getAdImageUrl() {
        return adImageUrl;
    }

    public void setAdImageUrl(String adImageUrl) {
        this.adImageUrl = adImageUrl;
    }

    public LocalDateTime getAdBeginTime() {
        return adBeginTime;
    }

    public void setAdBeginTime(LocalDateTime adBeginTime) {
        this.adBeginTime = adBeginTime;
    }

    public LocalDateTime getAdEndTime() {
        return adEndTime;
    }

    public void setAdEndTime(LocalDateTime adEndTime) {
        this.adEndTime = adEndTime;
    }

    public LocalDateTime getAdAddTime() {
        return adAddTime;
    }

    public void setAdAddTime(LocalDateTime adAddTime) {
        this.adAddTime = adAddTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.adId;
    }

    @Override
    public String toString() {
        return "Ad{" +
        "adId=" + adId +
        ", adTitle=" + adTitle +
        ", adUrl=" + adUrl +
        ", adSort=" + adSort +
        ", adTypeId=" + adTypeId +
        ", adImageUrl=" + adImageUrl +
        ", adBeginTime=" + adBeginTime +
        ", adEndTime=" + adEndTime +
        ", adAddTime=" + adAddTime +
        "}";
    }
}
