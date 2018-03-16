package com.learn.web.extra.jcaptcha;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;

import javax.servlet.http.HttpServletRequest;

/**
 * JCaptcha工具类
 * <p>
 * 提供相应的API来验证当前请求输入的验证码是否正确
 * </p>
 * 
 * @author JeeLearner
 * @date 2018年3月14日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class JCaptcha {
	public static final MyManageableImageCaptchaService captchaService = new MyManageableImageCaptchaService(
			new FastHashMapCaptchaStore(), new GMailEngine(), 180, 100000, 75000);

	/**
	 * 验证当前请求输入的验证码是否正确 并从CaptchaService中删除已经生成的验证码
	 * 
	 * @param request
	 * @param userCaptchaResponse
	 * @return
	 */
	public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse) {
		if (request.getSession(false) == null)
			return false;

		boolean validated = false;
		try {
			String id = request.getSession().getId();
			validated = captchaService.validateResponseForID(id, userCaptchaResponse).booleanValue();
			if(!validated){
				request.setAttribute("jcaptchaErr", "jcaptchaError");
			}
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		return validated;
	}

	/**
	 * 验证当前请求输入的验证码是否正确 但不从CaptchaService中删除已经生成的验证码（比如AJAX验证时可以使用，防止多次生成验证码）
	 * 
	 * @param request
	 * @param userCaptchaResponse
	 * @return
	 */
	public static boolean hasCaptcha(HttpServletRequest request, String userCaptchaResponse) {
		if (request.getSession(false) == null)
			return false;
		boolean validated = false;
		try {
			String id = request.getSession().getId();
			validated = captchaService.hasCapcha(id, userCaptchaResponse);
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		return validated;
	}

}
