package com.learn.common.maintain.icon.dao;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.maintain.icon.entity.Icon;

public interface IconDao extends BaseRepository<Icon, Long> {

	/**
	 * 通过标识查询图标
	 * @author JeeLearner
	 * @date 2018年4月11日
	 * @param identity
	 * @return
	 */
	Icon findByIdentity(String identity);
}
