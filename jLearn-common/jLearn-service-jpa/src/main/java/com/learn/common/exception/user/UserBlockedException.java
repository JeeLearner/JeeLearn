package com.learn.common.exception.user;

/**
 * 用户处于封禁状态
 * @author JeeLearner
 * @date 2018年3月7日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class UserBlockedException extends UserException {
    public UserBlockedException(String reason) {
        super("user.blocked", new Object[]{reason});
    }
}