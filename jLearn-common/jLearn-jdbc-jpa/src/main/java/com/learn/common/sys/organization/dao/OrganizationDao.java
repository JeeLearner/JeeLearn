package com.learn.common.sys.organization.dao;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.sys.organization.entity.Organization;

/**
 * 组织机构数据层
 * @author lyd
 * @date 2018年3月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface OrganizationDao extends BaseRepository<Organization, Long>  {
}