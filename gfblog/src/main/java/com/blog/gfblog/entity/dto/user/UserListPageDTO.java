package com.blog.gfblog.entity.dto.user;

import com.blog.gfblog.entity.dto.base.BasePageDTO;
import lombok.Data;

@Data
public class UserListPageDTO extends BasePageDTO {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户id
     */
    private String userId;
}
