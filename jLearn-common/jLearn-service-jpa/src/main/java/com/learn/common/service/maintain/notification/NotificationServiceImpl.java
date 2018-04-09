package com.learn.common.service.maintain.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.maintain.notification.dao.NotificationDataDao;
import com.learn.common.maintain.notification.entity.NotificationData;

/**
 * 通知数据service
 * @author JeeLearner
 * @date 2018年4月8日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Service
public class NotificationServiceImpl extends BaseService<NotificationData, Long> implements NotificationService {

	@Autowired
	private NotificationDataDao notificationDataDao;
	
	@Override
	public void markReadAll(final Long userId) {
		notificationDataDao.markReadAll(userId);
    }
	
	@Override
	public void markRead(final Long notificationId) {
        NotificationData data = findOne(notificationId);
        if(data == null || data.getRead().equals(Boolean.TRUE)) {
            return;
        }
        data.setRead(Boolean.TRUE);
        update(data);
    }
}
