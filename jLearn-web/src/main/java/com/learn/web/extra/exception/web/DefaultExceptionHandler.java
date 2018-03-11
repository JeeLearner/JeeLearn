package com.learn.web.extra.exception.web;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.learn.common.base.utils.LogUtils;
import com.learn.web.extra.exception.web.entity.ExceptionResponse;

@ControllerAdvice
public class DefaultExceptionHandler {

	@ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		LogUtils.logError("用户权限验证失败", e);
        ExceptionResponse exceptionResponse = ExceptionResponse.from(e);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", exceptionResponse);
        mv.setViewName("error/exception");

        return mv;
	}
}
