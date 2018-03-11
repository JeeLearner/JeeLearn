package com.learn.common.jdbc.jpa.dao.support.annotation;

import java.lang.annotation.*;

/**
 * 开启查询缓存
 *
 * @author JeeLearner
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableQueryCache {

    boolean value() default true;

}
