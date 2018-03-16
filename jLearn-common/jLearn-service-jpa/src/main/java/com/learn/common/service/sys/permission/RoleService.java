package com.learn.common.service.sys.permission;

import java.util.Set;

import com.learn.common.sys.permission.entity.Role;

public interface RoleService {

	/*
	 * 获取可用的角色列表
	 */
    public Set<Role> findShowRoles(Set<Long> roleIds);
}
