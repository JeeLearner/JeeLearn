package com.learn.common.service.maintain.icon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.maintain.icon.dao.IconDao;
import com.learn.common.maintain.icon.entity.Icon;

@Service
public class IconServiceImpl extends BaseService<Icon, Long> implements IconService {

	@Autowired
	private IconDao iconDao;
	
	@Override
	public Icon findByIdentity(String identity) {
		return iconDao.findByIdentity(identity);
	}

}
