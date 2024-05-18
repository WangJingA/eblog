package com.blog.gfblog.entity.dto.base;

import lombok.Data;

@Data
public class BasePageDTO {
    /**
     * 当前页
     */
    private Integer pageIndex;
    /**
     * 每一个页的数据量
     */
    private Integer pageSize;
}
