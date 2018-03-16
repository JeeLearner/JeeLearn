package com.learn.web.controller.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.common.base.constants.Constants;

@Controller
public class LoginFormController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { "/{login:login;?.*}" }) // spring3.2.2 bug see
														// http://jinnianshilongnian.iteye.com/blog/1831408
	public String loginForm(HttpServletRequest request, ModelMap model) {

		//表示退出
        if (!StringUtils.isEmpty(request.getParameter("logout"))) {
            model.addAttribute(Constants.MESSAGE, messageSource.getMessage("user.logout.success", null, null));
        }
		
		// 表示用户输入的验证码错误
		if (!StringUtils.isEmpty(request.getParameter("jcaptchaError"))) {
			model.addAttribute(Constants.ERROR, messageSource.getMessage("jcaptcha.validate.error", null, null));
		}
		
		//登录失败了 提取错误消息
        Exception shiroLoginFailureEx =
                (Exception) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (shiroLoginFailureEx != null) {
            //model.addAttribute(Constants.ERROR, shiroLoginFailureEx.getMessage());
        	model.addAttribute(Constants.ERROR, "输入的用户名/密码错误");
        }
		
		//如果用户直接到登录页面 先退出一下
        //原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
        // 这样会导致登录一直死循环
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            subject.logout();
        }

		return "front/login";
	}
}
