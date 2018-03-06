package com.learn.common.jdbc.jpa.entity.search.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 搜索异常类
 * @author lyd
 * @date 2018年3月5日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class SearchException extends NestedRuntimeException {

    public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
