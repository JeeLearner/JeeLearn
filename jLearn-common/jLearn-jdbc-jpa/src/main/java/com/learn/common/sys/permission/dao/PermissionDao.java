package com.learn.common.sys.permission.dao;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.sys.permission.entity.Permission;

/**
 * 权限数据层
 * @author lyd
 * @date 2018年3月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface PermissionDao extends BaseRepository<Permission, Long> {

}
