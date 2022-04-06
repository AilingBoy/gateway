package com.example.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 代志豪
 * 2022/3/17 10:24
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog {


    /**
     * 事件类型
     */
    int type() default 0;

    /**
     * 特殊接口
     */
    int re() default 0;

String desc() default "";


boolean bool() default true;

}
