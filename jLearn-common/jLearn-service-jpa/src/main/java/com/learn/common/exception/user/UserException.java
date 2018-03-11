package com.learn.common.exception.user;

import com.learn.common.base.exception.BaseException;

/**
 * 用户异常类
 * @author JeeLearner
 * @date 2018年3月7日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        //user模块
    	super("user", code, args, null);
    }

}