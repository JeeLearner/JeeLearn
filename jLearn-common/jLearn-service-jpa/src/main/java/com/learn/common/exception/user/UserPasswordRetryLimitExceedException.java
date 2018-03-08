package com.learn.common.exception.user;

/**
 * 密码次数超限异常
 * @author lyd
 * @date 2018年3月7日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class UserPasswordRetryLimitExceedException extends UserException {
    
	public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }
}