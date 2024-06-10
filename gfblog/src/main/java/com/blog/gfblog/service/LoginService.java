package com.blog.gfblog.service;

import com.blog.gfblog.common.ResponseResult;
import com.blog.gfblog.pojo.SysUser;

/**
 * 用户登录接口
 * @author husir
 * @date 2024/06/10
 */
public interface LoginService {
    /**
     * 登录
     * @param userDto
     * @return
     */
    ResponseResult login(SysUser userDto);

    /**
     * 登出
     * @return
     */
    ResponseResult logout();
}
