package com.learn.common.service.maintain.notification;

import com.learn.common.maintain.notification.entity.NotificationTemplate;

public interface NotificationTemplateService {

	/**
	 * 通过模板名称查询模板
	 * @author JeeLearner
	 * @date 2018年4月9日
	 * @param templateName
	 * @return
	 */
	NotificationTemplate findByName(String templateName);
}
