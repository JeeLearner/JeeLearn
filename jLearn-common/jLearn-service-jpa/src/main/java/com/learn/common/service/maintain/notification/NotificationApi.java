package com.learn.common.service.maintain.notification;

import java.util.List;
import java.util.Map;

import com.learn.common.exception.maintain.notification.TemplateNotFoundException;

/**
 * 通知接口
 * @author JeeLearner
 * @date 2018年4月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface NotificationApi {

	/**
	 * 发送通知
	 * @author JeeLearner
	 * @date 2018年4月9日
	 * @param userId 接收人用户编号
	 * @param templateName 模板名称
	 * @param context 模板需要的数据
	 * @throws TemplateNotFoundException 没有找到相应模板
	 */
	public void notify(Long userId, String templateName, Map<String, Object> context) throws TemplateNotFoundException;
	
	/**
	 * 首页推送前五条数据
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> topFiveNotification(Long userId);
}
