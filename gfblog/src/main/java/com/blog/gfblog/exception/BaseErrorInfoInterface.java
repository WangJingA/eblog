package com.blog.gfblog.exception;

import io.swagger.models.auth.In;

/**
 * 全局异常接口
 * @author husir
 * @date 2024/05/26
 */
public interface BaseErrorInfoInterface {
    /**
     * 获取状态码
     * @return
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMsg();
}
