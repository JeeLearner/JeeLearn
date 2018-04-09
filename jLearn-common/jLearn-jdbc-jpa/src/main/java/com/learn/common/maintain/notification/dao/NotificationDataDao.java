package com.learn.common.maintain.notification.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.maintain.notification.entity.NotificationData;

public interface NotificationDataDao extends BaseRepository<NotificationData, Long>{

	@Modifying
	@Query("update NotificationData o set o.read=true where userId=?1")
	void markReadAll(Long userId);
}
