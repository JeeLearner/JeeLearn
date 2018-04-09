package com.learn.common.maintain.notification.dao;

import org.springframework.data.jpa.repository.Query;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.maintain.notification.entity.NotificationTemplate;

public interface NotificationTemplateDao extends BaseRepository<NotificationTemplate, Long> {

	@Query("from NotificationTemplate o where name=?1")
	NotificationTemplate findByName(String name);
}
