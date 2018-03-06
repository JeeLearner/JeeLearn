package com.learn.common.jdbc.jpa.dao.support.annotation;

import java.lang.annotation.*;
import javax.persistence.criteria.JoinType;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryJoin {

    /**
     * 连接的名字
     * @return
     */
    String property();

    JoinType joinType();

}