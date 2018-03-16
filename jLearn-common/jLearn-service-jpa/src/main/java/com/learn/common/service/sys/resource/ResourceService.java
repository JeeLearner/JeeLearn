package com.learn.common.service.sys.resource;

import java.util.List;

import com.learn.common.sys.resource.entity.Resource;
import com.learn.common.sys.resource.entity.tmp.Menu;
import com.learn.common.sys.user.entity.User;

public interface ResourceService {

	/*
	 * 获取真是的资源标识   即 父亲:儿子
	 */
	public String findActualResourceIdentity(Resource resource);
	/*
	 * 根据user动态获取菜单
	 */
	public List<Menu> findMenus(User user);
	
	public Resource findOne(Long id);
}
