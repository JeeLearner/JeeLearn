package com.learn.common.service.sys.auth;

import java.util.Set;

import com.learn.common.sys.auth.entity.Auth;

public interface AuthService {

	/*
	 * 为用户添加角色授权
	 */
	public void addUserAuth(Long[] userIds, Auth m);
	/*
	 * 为组添加角色授权
	 */
	public void addGroupAuth(Long[] groupIds, Auth m);
	/*
	 * 为组织机构、工作职位添加角色授权
	 */
	public void addOrganizationJobAuth(Long[] organizationIds, Long[][] jobIds, Auth m);
	/*
	 * 根据用户信息获取 角色
	 */
	public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds, Set<Long[]> organizationJobIds);
	
	public Auth findOne(Long userId);
}
