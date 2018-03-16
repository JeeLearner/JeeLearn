package com.learn.common.service.sys.organization;

import java.util.Set;

public interface JobService {

	/*
	 * 过滤仅获取可显示的数据
	 */
	public void filterForCanShow(Set<Long> jobIds, Set<Long[]> organizationJobIds);
	/*
	 * 找工作职务的祖先
	 */
	public Set<Long> findAncestorIds(Iterable<Long> currentIds);
}
