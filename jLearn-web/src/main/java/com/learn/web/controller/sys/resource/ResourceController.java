package com.learn.web.controller.sys.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learn.common.base.constants.Constants;
import com.learn.common.base.enums.BooleanEnum;
import com.learn.common.service.sys.resource.ResourceService;
import com.learn.common.service.sys.user.UserService;
import com.learn.common.sys.organization.entity.Organization;
import com.learn.common.sys.resource.entity.Resource;
import com.learn.common.sys.user.entity.User;
import com.learn.common.sys.user.entity.UserStatus;
import com.learn.web.support.BaseCRUDController;
import com.learn.web.support.plugin.BaseTreeableController;

@Controller
@RequestMapping(value = "/admin/sys/resource")
public class ResourceController extends BaseTreeableController<Resource, Long> {

	public ResourceController() {
		setResourceIdentity("sys:resource");
	}

	@Autowired
	private ResourceService resourceService;
	

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
            Resource resource = baseService.findOne(id);
            resource.setShow(newStatus);
            baseService.update(resource);
        }

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "操作成功！");
        return "redirect:" + request.getAttribute(Constants.BACK_URL);
    }

}
