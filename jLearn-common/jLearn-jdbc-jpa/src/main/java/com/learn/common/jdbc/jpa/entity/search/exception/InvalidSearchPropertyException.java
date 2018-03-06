package com.learn.common.jdbc.jpa.entity.search.exception;

/**
 * 搜索属性异常类
 * @author lyd
 * @date 2018年3月5日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public final class InvalidSearchPropertyException extends SearchException {

    public InvalidSearchPropertyException(String searchProperty, String entityProperty) {
        this(searchProperty, entityProperty, null);
    }

    public InvalidSearchPropertyException(String searchProperty, String entityProperty, Throwable cause) {
        super("Invalid Search Property [" + searchProperty + "] Entity Property [" + entityProperty + "]", cause);
    }


}
