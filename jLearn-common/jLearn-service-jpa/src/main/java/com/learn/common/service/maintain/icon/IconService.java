package com.learn.common.service.maintain.icon;

import com.learn.common.maintain.icon.entity.Icon;

public interface IconService {

	/**
	 * 通过标识查询图标
	 * @author JeeLearner
	 * @date 2018年4月11日
	 * @param identity
	 * @return
	 */
	public Icon findByIdentity(String identity);
}
