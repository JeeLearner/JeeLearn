package com.learn.web.extra.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.common.base.utils.LogUtils;

/**
 * 记录访问日志 过滤器
 * @author JeeLearner
 * @date 2018年3月31日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class AccessLogFilter extends BaseFilter{

	@Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LogUtils.logAccess(request);
        chain.doFilter(request, response);
    }
}
