package com.learn.common.service.sys.auth;

import java.util.Set;

import com.learn.common.sys.permission.entity.Role;
import com.learn.common.sys.user.entity.User;

/**
 * 分组、组织机构、用户、新增、修改、删除时evict缓存
 * <p/>
 * 获取用户授权的角色及组装好的权限
 * @author JeeLearner
 * @date 2018年3月12日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface UserAuthService {

	/*
	 * 根据user查询角色集合
	 */
	public Set<Role> findRoles(User user);
	
	/*
	 * 根据用户获取String类型的角色集合
	 */
	public Set<String> findStringRoles(User user);
	
	/*
	 * 根据user获取 权限字符串 如sys:admin:create
	 */
	public Set<String> findStringPermissions(User user);
}
