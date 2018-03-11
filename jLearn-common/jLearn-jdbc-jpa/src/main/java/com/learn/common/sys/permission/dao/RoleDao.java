package com.learn.common.sys.permission.dao;

import org.springframework.data.jpa.repository.Query;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.sys.permission.entity.Role;
import com.learn.common.sys.permission.entity.RoleResourcePermission;

/**
 * 角色数据层
 * @author JeeLearner
 * @date 2018年3月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface RoleDao extends BaseRepository<Role, Long> {

    @Query("from RoleResourcePermission where role=?1 and resourceId=?2")
    RoleResourcePermission findRoleResourcePermission(Role role, Long resourceId);
}