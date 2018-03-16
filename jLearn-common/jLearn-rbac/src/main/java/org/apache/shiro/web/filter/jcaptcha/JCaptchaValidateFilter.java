package org.apache.shiro.web.filter.jcaptcha;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.learn.web.extra.jcaptcha.JCaptcha;

/**
 * 验证码过滤器
 * 
 * @author JeeLearner
 * @date 2018年3月14日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class JCaptchaValidateFilter extends AccessControlFilter {

	private boolean jcaptchaEnabled = true;
	private String jcaptchaParam = "jcaptchaCode";
	private String jcaptchaErrorUrl;

	/**
	 * 是否开启jcaptcha
	 *
	 * @param jcaptchaEbabled
	 */
	public void setJcaptchaEbabled(boolean jcaptchaEnabled) {
		this.jcaptchaEnabled = jcaptchaEnabled;
	}

	/**
	 * 前台传入的验证码
	 *
	 * @param jcaptchaParam
	 */
	public void setJcaptchaParam(String jcaptchaParam) {
		this.jcaptchaParam = jcaptchaParam;
	}

	public void setJcaptchaErrorUrl(String jcaptchaErrorUrl) {
		this.jcaptchaErrorUrl = jcaptchaErrorUrl;
	}

	public String getJcaptchaErrorUrl() {
		return jcaptchaErrorUrl;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 验证码禁用 或不是表单提交 允许访问
		if (jcaptchaEnabled == false || !"post".equals(httpServletRequest.getMethod().toLowerCase())) {
			return true;
		}
		return JCaptcha.validateResponse(httpServletRequest, httpServletRequest.getParameter(jcaptchaParam));
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		redirectToLogin(request, response);
        return true;
	}

	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		request.setAttribute("jcaptchaEnabled", jcaptchaEnabled);
		return super.onPreHandle(request, response, mappedValue);
	}
	
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, getJcaptchaErrorUrl());
    }

}
