package com.learn.common.exception.maintain.notification;

import com.learn.common.base.exception.BaseException;

/**
 * excel模板异常
 * @author JeeLearner
 * @date 2018年4月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class TemplateException extends BaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 377315527529193348L;

	public TemplateException(final String code, final Object[] args) {
        super("notification", code, args);
    }
}
