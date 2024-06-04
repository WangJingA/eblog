package com.blog.gfblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author husir
 * @since 2024-06-04
 */
public class AdType extends Model<AdType> {

    private static final long serialVersionUID = 1L;

    /**
     * 广告类型id
     */
    @TableId(value = "ad_type_id", type = IdType.AUTO)
    private String adTypeId;

    /**
     * 广告标题
     */
    private String adTypeTitle;

    /**
     * 广告标识：	0：首页顶部广告、1：轮播图广告、2：文章详情广告	3：管理首页轮播
     */
    private String adTypeTag;

    /**
     * 广告排序，升序排序
     */
    private Integer adTypeSort;

    /**
     * 广告类型添加时间
     */
    private LocalDateTime addTypeAddTime;


    public String getAdTypeId() {
        return adTypeId;
    }

    public void setAdTypeId(String adTypeId) {
        this.adTypeId = adTypeId;
    }

    public String getAdTypeTitle() {
        return adTypeTitle;
    }

    public void setAdTypeTitle(String adTypeTitle) {
        this.adTypeTitle = adTypeTitle;
    }

    public String getAdTypeTag() {
        return adTypeTag;
    }

    public void setAdTypeTag(String adTypeTag) {
        this.adTypeTag = adTypeTag;
    }

    public Integer getAdTypeSort() {
        return adTypeSort;
    }

    public void setAdTypeSort(Integer adTypeSort) {
        this.adTypeSort = adTypeSort;
    }

    public LocalDateTime getAddTypeAddTime() {
        return addTypeAddTime;
    }

    public void setAddTypeAddTime(LocalDateTime addTypeAddTime) {
        this.addTypeAddTime = addTypeAddTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.adTypeId;
    }

    @Override
    public String toString() {
        return "AdType{" +
        "adTypeId=" + adTypeId +
        ", adTypeTitle=" + adTypeTitle +
        ", adTypeTag=" + adTypeTag +
        ", adTypeSort=" + adTypeSort +
        ", addTypeAddTime=" + addTypeAddTime +
        "}";
    }
}
