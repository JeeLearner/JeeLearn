package com.learn.common.sys.auth.dao;

import java.util.Set;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.sys.auth.entity.Auth;
import com.learn.common.sys.organization.entity.Job;

public interface AuthDao extends BaseRepository<Auth, Long>{

	Auth findByUserId(Long userId);

    Auth findByGroupId(Long groupId);

    Auth findByOrganizationIdAndJobId(Long organizationId, Long jobId);

    ///////////委托给AuthDaoImpl实现
    public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds, Set<Long[]> organizationJobIds);
}
