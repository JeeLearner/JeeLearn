package com.learn.common.service.sys.group;

import java.util.Map;
import java.util.Set;

import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.sys.group.entity.Group;

public interface GroupService {

	public Set<Map<String, Object>> findIdAndNames(Searchable searchable, String groupName);
	
	public Set<Long> findShowGroupIds(Long userId, Set<Long> organizationIds);
	
	public Group findOne(Long id);
}
