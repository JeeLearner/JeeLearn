package com.learn.common.base.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.learn.common.base.constants.Constants;

/**
 * <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 * @author JeeLearner
 * @date 2018年3月19日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

	/**
	 * 当前用户在request中的名字 默认{@link Constants#CURRENT_USER}
	 * @author JeeLearner
	 * @date 2018年3月19日
	 * @return
	 */
	String value() default Constants.CURRENT_USER;
}
