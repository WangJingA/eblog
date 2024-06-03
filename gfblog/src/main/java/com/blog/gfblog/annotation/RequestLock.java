package com.blog.gfblog.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 防止表单重复提交锁
 *
 * @author husir
 * @date  2024/05/18 16:32
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequestLock {
    /**
     * redis锁前缀
     *
     * @return 默认为空，但不可为空
     */
    String prefix() default "";

    /**
     * redis锁过期时间
     *
     * @return 默认2秒
     */
    int expire() default 2;

    /**
     * redis锁过期时间单位
     *
     * @return 默认单位为秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * redis  key分隔符
     *
     * @return 分隔符
     */
    String delimiter() default ":";
}
