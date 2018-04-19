package com.learn.common.service.sys.organization;

import java.util.List;
import java.util.Set;

import com.learn.common.sys.organization.entity.Organization;

public interface OrganizationService {

	/*
	 * 过滤仅获取可显示的数据
	 */
	public void filterForCanShow(Set<Long> organizationIds, Set<Long[]> organizationJobIds);
	
	public List<Organization> findAll();
	
	/*
	 * 找组织机构的祖先Ids
	 */
	public Set<Long> findAncestorIds(Iterable<Long> currentIds);
	/*
	 * 找组织机构的祖先
	 */
	public List<Organization> findAncestor(String parentIds);
	
	public Organization findOne(Long organizationId);
	
	
}
