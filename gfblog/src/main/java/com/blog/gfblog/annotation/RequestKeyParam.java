package com.blog.gfblog.annotation;

import java.lang.annotation.*;

/**
 * @author husir
 * @date  2024/05/18 16:32
 * 表单防止重复提交-redis-key,默认空
 */
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestKeyParam {
    String name() default "";
}
