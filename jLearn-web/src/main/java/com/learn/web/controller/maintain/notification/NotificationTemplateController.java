package com.learn.web.controller.maintain.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.common.base.web.validate.ValidateResponse;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.maintain.notification.entity.NotificationSystem;
import com.learn.common.maintain.notification.entity.NotificationTemplate;
import com.learn.common.service.maintain.notification.NotificationTemplateService;
import com.learn.web.extra.bind.annotation.PageableDefaults;
import com.learn.web.extra.bind.annotation.SearchableDefaults;
import com.learn.web.support.BaseCRUDController;

/**
 * 通知模板 控制器
 * @author JeeLearner
 * @date 2018年4月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller
@RequestMapping(value = "/admin/maintain/notification/template")
public class NotificationTemplateController extends BaseCRUDController<NotificationTemplate, Long> {

	public NotificationTemplateController() {
		setResourceIdentity("maintain:notificationTemplate");
	}
	
	@Autowired
	private NotificationTemplateService notificationTemplateService;

	@Override
	@RequestMapping(method = RequestMethod.GET)
	@PageableDefaults(sort = "id=desc")
	@SearchableDefaults(value = "deleted_eq=true", merge = true)
	public String list(Model model, Searchable searchable) {
		return super.list(model, searchable);
	}
	
	/**
	 * 异步检验
	 * 验证返回格式
     * 单个：[fieldId, 1|0, msg]
     * 多个：[[fieldId, 1|0, msg],[fieldId, 1|0, msg]]
     *
	 * @author JeeLearner
	 * @date 2018年4月10日
	 * @param fieldId
	 * @param fieldValue
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	@ResponseBody
	public Object validate(@RequestParam("fieldId") String fieldId, 
			@RequestParam("fieldValue") String fieldValue,
            @RequestParam(value = "id", required = false) Long id) {
		ValidateResponse response = ValidateResponse.newInstance();
		NotificationTemplate template = notificationTemplateService.findByName(fieldValue);
		//新增 || 修改
		if (template == null || (template.getId().equals(id) && template.getName().equals(fieldValue))) {
            //如果msg 不为空 将弹出提示框
            response.validateSuccess(fieldId, "");
        } else {
            response.validateFail(fieldId, "该名称已被其他模板使用");
        }
		return response.result();
	}
	
	 /**
     * 验证失败返回true
     *
     * @param m
     * @param result
     * @return
     */
    @Override
    protected boolean hasError(NotificationTemplate m, BindingResult result) {
        Assert.notNull(m);

        NotificationTemplate template = notificationTemplateService.findByName(m.getName());
        if (template == null || (template.getId().equals(m.getId()) && template.getName().equals(m.getName()))) {
            //success
        } else {
            result.rejectValue("name", "该名称已被其他模板使用");
        }
        return result.hasErrors();
    }
	
	@Override
    protected void setCommonData(Model model) {
        model.addAttribute("notificationSystems", NotificationSystem.values());
    }
}
