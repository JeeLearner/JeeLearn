package com.learn.common.jdbc.jpa.dao.support.annotation;

import java.lang.annotation.*;

import com.learn.common.jdbc.jpa.dao.callback.SearchCallback;

/**
 * 覆盖默认的根据条件查询数据
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SearchableQuery {

    /**
     * 覆盖默认的查询所有ql
     *
     * @return
     */
    String findAllQuery() default "";

    /**
     * 覆盖默认的统计所有ql
     *
     * @return
     */
    String countAllQuery() default "";

    /**
     * 给ql拼条件及赋值的回调类型
     *
     * @return com.learn.common.jdbc.jpa.dao.callback.SearchCallback子类
     */
    Class<? extends SearchCallback> callbackClass() default SearchCallback.class;


    QueryJoin[] joins() default {};


}