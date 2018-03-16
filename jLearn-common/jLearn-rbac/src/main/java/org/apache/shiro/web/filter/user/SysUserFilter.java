package org.apache.shiro.web.filter.user;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.common.base.constants.Constants;
import com.learn.common.service.sys.user.UserService;
import com.learn.common.sys.user.entity.User;
import com.learn.common.sys.user.entity.UserStatus;

/**
 * * 验证用户过滤器 1、用户是否删除 2、用户是否锁定
 * 
 * @author JeeLearner
 * @date 2018年3月13日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class SysUserFilter extends AccessControlFilter {

	@Autowired
	private UserService userService;

	/**
	 * 用户删除了后 重定向的地址
	 */
	private String userNotfoundUrl;
	/**
	 * 用户锁定后重定向的地址
	 */
	private String userBlockedUrl;
	/**
	 * 未知错误
	 */
	private String userUnknownErrorUrl;

	/**
	 * 是否允许访问,返回true表示允许
	 * 
	 * @author JeeLearner
	 * @date 2018年3月14日
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws Exception
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		User user = (User) request.getAttribute(Constants.CURRENT_USER);
		if (user == null) {
			return true;
		}
		if (Boolean.TRUE.equals(user.getDeleted()) || user.getStatus() == UserStatus.blocked) {
			getSubject(request, response).logout();
			saveRequestAndRedirectToLogin(request, response);
			return false;
		}
		return true;
	}

	/**
	 * 访问拒绝时是否自己处理, 
	 * <p>如果返回true表示自己不处理且继续拦截器链执行</p>
	 *  <p>返回false表示自己已经处理了（比如重定向到另一个页面）</P>
	 * 
	 * @author JeeLearner
	 * @date 2018年3月14日
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		getSubject(request, response).logout();
		saveRequestAndRedirectToLogin(request, response);
		return true;
	}

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject == null) {
			return true;
		}

		String username = (String) subject.getPrincipal();
		// 此处注意缓存 防止大量的查询db
		User user = userService.findByUsername(username);
		// 把当前用户放到session中
		request.setAttribute(Constants.CURRENT_USER, user);
		// druid监控需要
		((HttpServletRequest) request).getSession().setAttribute(Constants.CURRENT_USERNAME, username);

		return true;
	}
	
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		User user = (User) request.getAttribute(Constants.CURRENT_USER);
        String url = null;
        if (Boolean.TRUE.equals(user.getDeleted())) {
            url = getUserNotfoundUrl();
        } else if (user.getStatus() == UserStatus.blocked) {
            url = getUserBlockedUrl();
        } else {
            url = getUserUnknownErrorUrl();
        }

        WebUtils.issueRedirect(request, response, url);
	}

	
	// ========getter/setter
	public String getUserBlockedUrl() {
		return userBlockedUrl;
	}

	public void setUserBlockedUrl(String userBlockedUrl) {
		this.userBlockedUrl = userBlockedUrl;
	}

	public String getUserUnknownErrorUrl() {
		return userUnknownErrorUrl;
	}

	public void setUserUnknownErrorUrl(String userUnknownErrorUrl) {
		this.userUnknownErrorUrl = userUnknownErrorUrl;
	}

	public String getUserNotfoundUrl() {
		return userNotfoundUrl;
	}

	public void setUserNotfoundUrl(String userNotfoundUrl) {
		this.userNotfoundUrl = userNotfoundUrl;
	}
}
