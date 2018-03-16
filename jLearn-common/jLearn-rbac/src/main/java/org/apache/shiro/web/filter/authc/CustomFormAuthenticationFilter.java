package org.apache.shiro.web.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.common.service.sys.user.UserService;
import com.learn.common.sys.user.entity.User;

/**
 * 基于几点修改： 1、onLoginFailure 时 把异常添加到request attribute中 而不是异常类名 2、登录成功时：成功页面重定向：
 * 2.1、如果前一个页面是登录页面，-->2.3 2.2、如果有SavedRequest 则返回到SavedRequest
 * 2.3、否则根据当前登录的用户决定返回到管理员首页/前台首页
 * 
 * @author JeeLearner
 * @date 2018年3月13日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private UserService userService;

	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}

	/**
	 * 验证码验证的Shiro过滤器，
	 *   在用于身份认证的过滤器之前运行 如果验证码验证拦截器失败了，就不再走身份验证拦截器了
	 * 
	 * @author JeeLearner
	 * @date 2018年3月16日
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws Exception
	 * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if (request.getAttribute("jcaptchaErr") != null) {
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}

	/**
	 * 默认的成功地址
	 */
	private String defaultSuccessUrl;
	/**
	 * 管理员默认的成功地址
	 */
	private String adminDefaultSuccessUrl;

	/**
	 * 根据用户选择成功地址
	 *
	 * @return
	 */
	@Override
	public String getSuccessUrl() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		User user = userService.findByUsername(username);
		if (user != null && Boolean.TRUE.equals(user.getAdmin())) {
			return getAdminDefaultSuccessUrl();
		}
		return getDefaultSuccessUrl();
	}

	// --------getter/setter
	public String getDefaultSuccessUrl() {
		return defaultSuccessUrl;
	}

	public void setDefaultSuccessUrl(String defaultSuccessUrl) {
		this.defaultSuccessUrl = defaultSuccessUrl;
	}

	public String getAdminDefaultSuccessUrl() {
		return adminDefaultSuccessUrl;
	}

	public void setAdminDefaultSuccessUrl(String adminDefaultSuccessUrl) {
		this.adminDefaultSuccessUrl = adminDefaultSuccessUrl;
	}

}
