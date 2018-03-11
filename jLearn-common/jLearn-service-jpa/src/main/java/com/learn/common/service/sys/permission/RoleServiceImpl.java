package com.learn.common.service.sys.permission;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.sys.permission.dao.RoleDao;
import com.learn.common.sys.permission.entity.Role;
import com.learn.common.sys.permission.entity.RoleResourcePermission;

@Service
public class RoleServiceImpl extends BaseService<Role, Long> implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * update
	 * @author JeeLearner
	 * @date 2018年3月9日
	 * @param role
	 * @return
	 * @see com.learn.common.jdbc.jpa.service.BaseService#update(com.learn.common.jdbc.jpa.entity.AbstractEntity)
	 */
	@Override
    public Role update(Role role) {
        List<RoleResourcePermission> localResourcePermissions = role.getResourcePermissions();
        for (int i = 0, l = localResourcePermissions.size(); i < l; i++) {
            RoleResourcePermission localResourcePermission = localResourcePermissions.get(i);
            localResourcePermission.setRole(role);
            RoleResourcePermission dbResourcePermission = findRoleResourcePermission(localResourcePermission);
            if (dbResourcePermission != null) {//出现在先删除再添加的情况
                dbResourcePermission.setRole(localResourcePermission.getRole());
                dbResourcePermission.setResourceId(localResourcePermission.getResourceId());
                dbResourcePermission.setPermissionIds(localResourcePermission.getPermissionIds());
                localResourcePermissions.set(i, dbResourcePermission);
            }
        }
        return super.update(role);
    }
	
	/**
	 * 获取可用的角色列表
	 * @author JeeLearner
	 * @date 2018年3月9日
	 * @param roleIds
	 * @return
	 */
    public Set<Role> findShowRoles(Set<Long> roleIds) {

        Set<Role> roles = Sets.newHashSet();

        //TODO 如果角色很多 此处应该写查询
        for (Role role : findAll()) {
            if (Boolean.TRUE.equals(role.getShow()) && roleIds.contains(role.getId())) {
                roles.add(role);
            }
        }
        return roles;
    }

	private RoleResourcePermission findRoleResourcePermission(RoleResourcePermission roleResourcePermission) {
        return roleDao.findRoleResourcePermission(
                roleResourcePermission.getRole(), roleResourcePermission.getResourceId());
    }
	
}
