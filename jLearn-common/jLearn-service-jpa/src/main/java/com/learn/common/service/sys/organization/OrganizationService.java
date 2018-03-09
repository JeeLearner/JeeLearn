package com.learn.common.service.sys.organization;

import java.util.Set;

public interface OrganizationService {

	/*
	 * 过滤仅获取可显示的数据
	 */
	public void filterForCanShow(Set<Long> organizationIds, Set<Long[]> organizationJobIds);
}
