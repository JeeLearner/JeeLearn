package com.learn.web.extra.taglib;

import java.util.Iterator;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement.Organization;
import com.learn.common.base.utils.SpringUtils;
import com.learn.common.service.sys.organization.JobService;
import com.learn.common.service.sys.organization.OrganizationService;
import com.learn.common.service.sys.permission.PermissionService;
import com.learn.common.service.sys.permission.RoleService;
import com.learn.common.service.sys.resource.ResourceService;
import com.learn.common.sys.organization.entity.Job;
import com.learn.common.sys.permission.entity.Permission;
import com.learn.common.sys.permission.entity.Role;
import com.learn.common.sys.resource.entity.Resource;

/**
 * 提供el中可以使用的一些函数
 * @author JeeLearner
 * @date 2018年3月12日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class JLearnFunction {

	public static boolean in(Iterable iterable, Object obj) {
		if (iterable == null) {
			return false;
		}
		Iterator iterator = iterable.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断指定id的组织机构是否存在
	 * @param id
	 * @param onlyDisplayShow
	 *            是否仅显示可见的
	 * @return
	 */
	/*public static boolean existsOrganization(Long id, Boolean onlyDisplayShow) {
		Organization organization = SpringUtils.getBean(OrganizationService.class).findOne(id);
		if (organization == null) {
			return false;
		}
		if (Boolean.TRUE.equals(onlyDisplayShow) && Boolean.FALSE.equals(organization.getShow())) {
			return false;
		}
		return true;
	}*/

	/**
	 * 判断指定id的工作职务是否存在
	 * @param id
	 * @param onlyDisplayShow
	 *            是否仅显示可见的
	 * @return
	 */
	/*public static boolean existsJob(Long id, Boolean onlyDisplayShow) {
		Job job = SpringUtils.getBean(JobService.class).findOne(id);
		if (job == null) {
			return false;
		}
		if (Boolean.TRUE.equals(onlyDisplayShow) && Boolean.FALSE.equals(job.getShow())) {
			return false;
		}
		return true;
	}*/

	/**
	 * 判断指定id的资源是否存在
	 * @param id
	 * @param onlyDisplayShow
	 *            是否仅显示可见的
	 * @return
	 */
	/*public static boolean existsResource(Long id, Boolean onlyDisplayShow) {
		Resource resource = SpringUtils.getBean(ResourceService.class).findOne(id);
		if (resource == null) {
			return false;
		}
		if (Boolean.TRUE.equals(onlyDisplayShow) && Boolean.FALSE.equals(resource.getShow())) {
			return false;
		}
		return true;
	}*/

	/**
	 * 判断指定id的权限是否存在
	 * @param id
	 * @param onlyDisplayShow
	 *            是否仅显示可见的
	 * @return
	 */
	/*public static boolean existsPermission(Long id, Boolean onlyDisplayShow) {
		Permission permission = SpringUtils.getBean(PermissionService.class).findOne(id);
		if (permission == null) {
			return false;
		}
		if (Boolean.TRUE.equals(onlyDisplayShow) && Boolean.FALSE.equals(permission.getShow())) {
			return false;
		}
		return true;
	}*/

	/**
	 * 判断指定id的角色是否存在
	 * @param id
	 * @param onlyDisplayShow
	 *            是否仅显示可见的
	 * @return
	 */
	/*public static boolean existsRole(Long id, Boolean onlyDisplayShow) {
		Role role = SpringUtils.getBean(RoleService.class).findOne(id);
		if (role == null) {
			return false;
		}
		if (Boolean.TRUE.equals(onlyDisplayShow) && Boolean.FALSE.equals(role.getShow())) {
			return false;
		}
		return true;
	}*/

}
