package com.learn.common.service.maintain.notification;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.maintain.notification.entity.NotificationData;

public interface NotificationService {

	/**
	 * 标记所有通知已读
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 */
	public void markReadAll(final Long userId);
	/**
	 * 标记一条通知已读
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param notificationId
	 */
	public void markRead(final Long notificationId);

	public Page<NotificationData> findAll(Pageable pageable);
	
	public Page<NotificationData> findAll(Searchable searchable);

	public NotificationData save(NotificationData m);
}
