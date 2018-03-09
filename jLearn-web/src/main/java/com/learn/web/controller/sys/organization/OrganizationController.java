package com.learn.web.controller.sys.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.common.service.sys.organization.OrganizationService;
import com.learn.common.sys.organization.entity.Organization;
import com.learn.common.sys.user.entity.User;
import com.learn.web.support.plugin.BaseTreeableController;

@Controller
@RequestMapping(value = "/admin/sys/organization")
public class OrganizationController extends BaseTreeableController<Organization, Long> {

	@Autowired
	private OrganizationService organizationService;
	
	/*@RequestMapping("")
	@ResponseBody
	public String testService(){
		//User user = organizationService.filterForCanShow(, organizationJobIds);;
		return user.getEmail();
		
	}*/
	
}
