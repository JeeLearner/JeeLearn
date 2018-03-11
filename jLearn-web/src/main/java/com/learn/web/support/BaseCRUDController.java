package com.learn.web.support;

import com.learn.common.base.bind.annotation.PageableDefaults;
import com.learn.common.base.constants.Constants;
import com.learn.common.jdbc.jpa.entity.AbstractEntity;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.web.support.permission.PermissionList;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * 基础CRUD 控制器
 * @author JeeLearner
 * @date 2018年3月11日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public abstract class BaseCRUDController<M extends AbstractEntity, ID extends Serializable>
		extends BaseController<M, ID> {

	@Autowired
	private BaseService<M, ID> baseService;

	private boolean listAlsoSetCommonData = false;

	protected PermissionList permissionList = null;

	/**
	 * 列表也设置common data
	 */
	public void setListAlsoSetCommonData(boolean listAlsoSetCommonData) {
		this.listAlsoSetCommonData = listAlsoSetCommonData;
	}

	/**
	 * 权限前缀：如sys:user 则生成的新增权限为 sys:user:create
	 */
	public void setResourceIdentity(String resourceIdentity) {
		if (!StringUtils.isEmpty(resourceIdentity)) {
			permissionList = PermissionList.newPermissionList(resourceIdentity);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@PageableDefaults(sort = "id=desc")
	public String list(Model model, Searchable searchable) {
		if (permissionList != null) {
			this.permissionList.assertHasViewPermission();
		}

		model.addAttribute("page", baseService.findAll(searchable));
		if (listAlsoSetCommonData) {
			setCommonData(model);
		}
		return viewName("list");
	}

	/**
	 * 仅返回表格数据
	 *
	 * @param searchable
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "table=true")
	@PageableDefaults(sort = "id=desc")
	public String listTable(Searchable searchable, Model model) {
		list(model, searchable);
		return viewName("listTable");
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id") M m) {

		if (permissionList != null) {
			this.permissionList.assertHasViewPermission();
		}

		setCommonData(model);
		model.addAttribute("m", m);
		model.addAttribute(Constants.OP_NAME, "查看");
		return viewName("editForm");
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {

		if (permissionList != null) {
			this.permissionList.assertHasCreatePermission();
		}

		setCommonData(model);
		model.addAttribute(Constants.OP_NAME, "新增");
		if (!model.containsAttribute("m")) {
			model.addAttribute("m", newModel());
		}
		return viewName("editForm");
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Model model, @Valid @ModelAttribute("m") M m, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (permissionList != null) {
			this.permissionList.assertHasCreatePermission();
		}

		if (hasError(m, result)) {
			return showCreateForm(model);
		}
		baseService.save(m);
		redirectAttributes.addFlashAttribute(Constants.MESSAGE, "新增成功");
		return redirectToUrl(null);
	}

	@RequestMapping(value = "{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") M m, Model model) {

		if (permissionList != null) {
			this.permissionList.assertHasUpdatePermission();
		}

		setCommonData(model);
		model.addAttribute(Constants.OP_NAME, "修改");
		model.addAttribute("m", m);
		return viewName("editForm");
	}

	@RequestMapping(value = "{id}/update", method = RequestMethod.POST)
	public String update(Model model, @Valid @ModelAttribute("m") M m, BindingResult result,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = Constants.BACK_URL, required = false) String backURL) {

		if (permissionList != null) {
			this.permissionList.assertHasUpdatePermission();
		}

		if (hasError(m, result)) {
			return showUpdateForm(m, model);
		}
		baseService.update(m);
		redirectAttributes.addFlashAttribute(Constants.MESSAGE, "修改成功");
		return redirectToUrl(backURL);
	}
	
	@RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") M m, Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        setCommonData(model);
        model.addAttribute(Constants.OP_NAME, "删除");
        model.addAttribute("m", m);
        return viewName("editForm");
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    public String delete(
            @PathVariable("id") M m,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {


        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        baseService.delete(m);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(backURL);
    }
    
    @RequestMapping(value = "batch/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteInBatch(
            @RequestParam(value = "ids", required = false) ID[] ids,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {


        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }

        baseService.delete(ids);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(backURL);
    }
}
