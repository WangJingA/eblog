package com.blog.gfblog.entity.dto.user;

import com.blog.gfblog.pojo.Ad;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 首页数据
 */
@Data
@Accessors(chain = true)
public class SystemInfoAndUserDetailDTO {
    /**
     * 系统类型
     */
    private String systemType;
    /**
     * 服务地址
     */
    private String serviceIp;
    /**
     * 用户数量
     */
    private Integer userNumber;
    /**
     * 文章数量
     */
    private Integer articleNumber;
    /**
     * 标签数量
     */
    private Integer tagNumber;
    /**
     * 文章类型数量
     */
    private Integer articleTypeCount;

    /**
     * 首页轮播广告
     */
    private List<Ad> ads;
}
