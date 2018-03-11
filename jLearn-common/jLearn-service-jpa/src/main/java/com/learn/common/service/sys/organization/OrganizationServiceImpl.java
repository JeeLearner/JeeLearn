package com.learn.common.service.sys.organization;

import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.learn.common.plugin.service.BaseTreeableService;
import com.learn.common.sys.organization.entity.Organization;

@Service
public class OrganizationServiceImpl extends BaseTreeableService<Organization, Long> implements OrganizationService {

	/**
	 * 过滤仅获取可显示的数据
	 * @author JeeLearner
	 * @date 2018年3月9日
	 * @param organizationIds
	 * @param organizationJobIds
	 */
    public void filterForCanShow(Set<Long> organizationIds, Set<Long[]> organizationJobIds) {

        Iterator<Long> iter1 = organizationIds.iterator();

        while (iter1.hasNext()) {
            Long id = iter1.next();
            Organization o = findOne(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter1.remove();
            }
        }

        Iterator<Long[]> iter2 = organizationJobIds.iterator();

        while (iter2.hasNext()) {
            Long id = iter2.next()[0];
            Organization o = findOne(id);
            if (o == null || Boolean.FALSE.equals(o.getShow())) {
                iter2.remove();
            }
        }

    }
}
