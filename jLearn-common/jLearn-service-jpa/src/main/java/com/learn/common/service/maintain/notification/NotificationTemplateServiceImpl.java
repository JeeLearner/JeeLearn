package com.learn.common.service.maintain.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.maintain.notification.dao.NotificationTemplateDao;
import com.learn.common.maintain.notification.entity.NotificationTemplate;

@Service
public class NotificationTemplateServiceImpl extends BaseService<NotificationTemplate, Long> implements NotificationTemplateService {

	@Autowired
	private NotificationTemplateDao notificationTemplateDao;
	
	@Override
	public NotificationTemplate findByName(String templateName) {
		return notificationTemplateDao.findByName(templateName);
	}
}
