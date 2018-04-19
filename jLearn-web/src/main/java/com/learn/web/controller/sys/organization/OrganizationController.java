package com.learn.web.controller.sys.organization;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learn.common.base.constants.Constants;
import com.learn.common.service.sys.organization.OrganizationService;
import com.learn.common.sys.organization.entity.Organization;
import com.learn.web.support.plugin.BaseTreeableController;

@Controller
@RequestMapping(value = "/admin/sys/organization/organization")
public class OrganizationController extends BaseTreeableController<Organization, Long> {

	public OrganizationController() {
		setResourceIdentity("sys:organization");
	}

	@Autowired
	private OrganizationService organizationService;
	
	
	/**
	 * 改变状态
	 * <p>显示 or 隐藏</p>
	 * @author JeeLearner
	 * @date 2018年4月19日
	 * @param request
	 * @param newStatus
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/changeStatus/{newStatus}")
    public String changeStatus(
            HttpServletRequest request,
            @PathVariable("newStatus") Boolean newStatus,
            @RequestParam("ids") Long[] ids,
            RedirectAttributes redirectAttributes
    ) {

        this.permissionList.assertHasUpdatePermission();

        for (Long id : ids) {
            Organization organization = baseService.findOne(id);
            organization.setShow(newStatus);
            baseService.update(organization);
        }

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "操作成功！");

        return "redirect:" + request.getAttribute(Constants.BACK_URL);
    }

	
	
	//TODO 测试
	@RequestMapping("")
	@ResponseBody
	public int testService(){
		//User user = organizationService.filterForCanShow(, organizationJobIds);;
		List<Organization> list = organizationService.findAll();
		return list.size();
		
	}
	
}
