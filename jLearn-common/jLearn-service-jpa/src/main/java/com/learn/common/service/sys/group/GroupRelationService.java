package com.learn.common.service.sys.group;

import java.util.Set;

public interface GroupRelationService {

	public void appendRelation(Long groupId, Long[] organizationIds);
	
	public void appendRelation(Long groupId, Long[] userIds, Long[] startUserIds, Long[] endUserIds);
	
	public Set<Long> findGroupIds(Long userId, Set<Long> organizationIds);
}
