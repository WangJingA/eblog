package com.blog.gfblog.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 管理员登录参数接收类
 *
 * @author husir
 * @date 2024/05/18
 */
@Data
@ApiModel
@Accessors(chain = true)
public class AdminLoginDTO {
    /**
     * 管理员账户
     */
    private String adminAccount;
    /**
     * 管理员密码
     */
    private String adminPassword;
    /**
     * 验证码
     */
    private String vailCode;
}
