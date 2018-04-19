package com.learn.web.support.plugin;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Operation;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.learn.common.base.constants.Constants;
import com.learn.common.base.enums.BooleanEnum;
import com.learn.common.base.utils.TypeToUtil;
import com.learn.common.jdbc.jpa.entity.BaseEntity;
import com.learn.common.jdbc.jpa.entity.search.SearchOperator;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.plugin.entity.Treeable;
import com.learn.common.plugin.service.BaseTreeableService;
import com.learn.web.extra.bind.annotation.CurrentUser;
import com.learn.web.extra.bind.annotation.PageableDefaults;
import com.learn.web.support.BaseController;
import com.learn.web.support.permission.PermissionList;
import com.learn.web.support.plugin.entity.ZTree;

public abstract class BaseTreeableController<M extends BaseEntity<ID> & Treeable<ID>, ID extends Serializable> extends BaseController<M, ID> {

	@Autowired
	protected BaseTreeableService<M, ID> baseService;

    protected PermissionList permissionList = null;
    
    public void setResourceIdentity(String resourceIdentity){
    	if(!StringUtils.isEmpty(resourceIdentity)){
    		permissionList = PermissionList.newPermissionList(resourceIdentity);
    	}
    }
    
    protected void setCommonData(Model model) {
        model.addAttribute("booleanList", BooleanEnum.values());
    }
    
    /**
     * 进入主页面
     * @author JeeLearner
     * @date 2018年4月10日
     * @return
     */
    @RequestMapping(value = {"", "main"}, method = RequestMethod.GET)
    public String main() {
    	if(permissionList != null){
    		permissionList.assertHasViewPermission();
    	}
    	return viewName("main");
    }
    
    /**
     * 展示树结构
     * @author JeeLearner
     * @date 2018年4月10日
     * @param request
     * @param searchName
     * @param async
     * @param searchable
     * @param model
     * @return
     */
    @RequestMapping(value = "tree", method = RequestMethod.GET)
    public String tree(HttpServletRequest request,
            @RequestParam(value = "searchName", required = false) String searchName,
            @RequestParam(value = "async", required = false, defaultValue = "false") boolean async,
            Searchable searchable, Model model) {
    	if (permissionList != null) {
            permissionList.assertHasViewPermission();
        }
    	
    	List<M> models = null;
    	
    	if (!StringUtils.isEmpty(searchName)) {
    		searchable.addSearchParam("name_like", searchName);
    		models = baseService.findAllByName(searchable, null);
    		if(!async){ //非异步 查自己和子子孙孙
    			searchable.removeSearchFilter("name_like");
    			List<M> children = baseService.findChildren(models, searchable);
    			models.removeAll(children);
    			models.addAll(children);
    		} else { //异步模式只查自己
    			
    		}
    	} else {
    		if (!async) {  //非异步 查自己和子子孙孙
                models = baseService.findAllWithSort(searchable);
            } else {  //异步模式只查根 和 根一级节点
                models = baseService.findRootAndChild(searchable);
            }
    	}
    	model.addAttribute("trees",
                convertToZtreeList(
                        request.getContextPath(),
                        models,
                        async,
                        true));

        return viewName("tree");
    }
    
    //////////////////main页面右侧视图加载/////////////////////
    /**
     * 跳转到查看页面
     * @author JeeLearner
     * @date 2018年4月11日
     * @param m
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") M m, Model model) {
        if (permissionList != null) {
            permissionList.assertHasViewPermission();
        }

        setCommonData(model);
        model.addAttribute("m", m);
        model.addAttribute(Constants.OP_NAME, "查看");
        return viewName("editForm");
    }
    
    /**
     * 跳转到修改页面
     * @author JeeLearner
     * @date 2018年4月11日
     * @param m
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") M m, Model model, RedirectAttributes redirectAttributes) {

        if (permissionList != null) {
            permissionList.assertHasUpdatePermission();
        }
        if(m == null){
        	redirectAttributes.addFlashAttribute(Constants.ERROR, "您修改的数据不存在！");
        	return viewName("success");
        }
        
        setCommonData(model);
        model.addAttribute("m", m);
        model.addAttribute(Constants.OP_NAME, "修改");
        return viewName("editForm");
    }
    
    /**
     * 执行修改操作
     * @author JeeLearner
     * @date 2018年4月11日
     * @param m
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("m") M m, Model model, RedirectAttributes redirectAttributes, BindingResult result) {

        if (permissionList != null) {
            permissionList.assertHasUpdatePermission();
        }
        if(result.hasErrors()){
        	return updateForm(m, model, redirectAttributes);
        }
        baseService.update(m);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "修改成功");
        return redirectToUrl(viewName("success"));
    }
    
    /**
     * 跳转到删除页面
     * @author JeeLearner
     * @date 2018年4月11日
     * @param m
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String deleteForm(@PathVariable("id") M m, Model model) {
    	if (permissionList != null) {
            permissionList.assertHasDeletePermission();
        }
    	
    	setCommonData(model);
        model.addAttribute("m", m);
        model.addAttribute(Constants.OP_NAME, "删除");
        return viewName("editForm");
    }
    
    /**
     * 执行删除操作
     * @author JeeLearner
     * @date 2018年4月11日
     * @param m
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    public String deleteSelfAndChildren(@ModelAttribute("m") M m, Model model, RedirectAttributes redirectAttributes, BindingResult result) {

        if (permissionList != null) {
            permissionList.assertHasDeletePermission();
        }
        if(m.isRoot()){
        	result.reject("您删除的数据中包含根节点，根节点不能删除");
            return deleteForm(m, model);
        }
        baseService.deleteSelfAndChild(m);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(viewName("success"));
   }
    
    /**
     * 跳转到添加子节点页面
     * @author JeeLearner
     * @date 2018年4月11日
     * @param m
     * @param model
     * @return
     */
    @RequestMapping(value = "{parent}/appendChild", method = RequestMethod.GET)
    public String appendChildForm(@PathVariable("parent") M parent, Model model) {
    	if (permissionList != null) {
            permissionList.assertHasCreatePermission();
        }
    	
    	setCommonData(model);
    	if(!model.containsAttribute("child")){
    		model.addAttribute("child", newModel());
    	}
    	model.addAttribute("parent", parent);
        model.addAttribute(Constants.OP_NAME, "添加子节点");
        return viewName("appendChildForm");
    }
    
    /**
     * 添加子节点
     * @author JeeLearner
     * @date 2018年4月12日
     * @param parent
     * @param child
     * @param model
     * @param result
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "{parent}/appendChild", method = RequestMethod.POST)
    public String appendChildForm(@PathVariable("parent") M parent, 
    		@ModelAttribute("child") M child, 
    		Model model, BindingResult result, RedirectAttributes redirectAttributes) {
    	
    	if (permissionList != null) {
            permissionList.assertHasCreatePermission();
        }
    	setCommonData(model);
    	if(result.hasErrors()){
    		return appendChildForm(parent, model);
    	}
    	
    	baseService.appendChild(parent, child);
    	redirectAttributes.addFlashAttribute(Constants.MESSAGE, "添加子节点成功");
        return redirectToUrl(viewName("success"));
    }
    
    /**
     * 跳转到移动节点页面
     * @author JeeLearner
     * @date 2018年4月11日
     * @param m
     * @param model
     * @return
     */
    @RequestMapping(value = "{source}/move", method = RequestMethod.GET)
    @PageableDefaults(sort = {"parentIds=asc", "weight=asc"})
    public String moveForm(@PathVariable("source") M source, 
    		@RequestParam(value = "async", required = false, defaultValue = "false") boolean async,
    		Searchable searchable, Model model, HttpServletRequest request) {
    	if (permissionList != null) {
            permissionList.assertHasEditPermission();
        }
    	
    	List<M> models = null;
    	
    	//排除自己及子子孙孙
        searchable.addSearchFilter("id", SearchOperator.ne, source.getId());
        searchable.addSearchFilter(
                "parentIds",
                SearchOperator.notLike,
                source.makeSelfAsNewParentIds() + "%");

        if (!async) { //非异步
            models = baseService.findAllWithSort(searchable);
        } else {
            models = baseService.findRootAndChild(searchable);
        }

        model.addAttribute("trees", convertToZtreeList(
                request.getContextPath(),
                models,
                async,
                true));

        model.addAttribute(Constants.OP_NAME, "移动节点");

        return viewName("moveForm");
    }
    
    /**
     * 移动节点
     * @author JeeLearner
     * @date 2018年4月12日
     * @param request
     * @param async
     * @param source
     * @param target
     * @param moveType
     * @param searchable
     * @param model
     * @param redirectAttributes
     * @return
     */
    // TODO 报错
    @RequestMapping(value = "{source}/move", method = RequestMethod.POST)
    @PageableDefaults(sort = {"parentIds=asc", "weight=asc"})
    public String move(
            @RequestParam(value = "async", required = false, defaultValue = "false") boolean async,
            @PathVariable("source") M source,
            @RequestParam("target") M target,
            @RequestParam("moveType") String moveType,
            HttpServletRequest request,
            Searchable searchable,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (this.permissionList != null) {
            this.permissionList.assertHasEditPermission();
        }
        
        if (target.isRoot() && !moveType.equals("inner")) {
            model.addAttribute(Constants.ERROR, "不能移动到根节点之前或之后");
            return moveForm(source, async, searchable, model, request);
        }

        baseService.move(source, target, moveType);

        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "移动节点成功");
        return redirectToUrl(viewName("success"));
    }
    
    /**
     * 查询子节点
     * @author JeeLearner
     * @date 2018年4月12日
     * @param request
     * @param parent
     * @param searchable
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "{parent}/children", method = RequestMethod.GET)
    @PageableDefaults(sort = {"parentIds=asc", "weight=asc"})
    public String list(
            HttpServletRequest request,
            @PathVariable("parent") M parent,
            Searchable searchable, Model model) throws UnsupportedEncodingException {

        if (permissionList != null) {
            permissionList.assertHasViewPermission();
        }

        if (parent != null) {
            searchable.addSearchFilter("parentId", SearchOperator.eq, parent.getId());
        }

        model.addAttribute("page", baseService.findAll(searchable));

        return viewName("listChildren");
    }
    
    /**
     * 仅返回表格数据
     *
     * @param searchable
     * @param model
     * @return
     */
    @RequestMapping(value = "{parent}/children", headers = "table=true", method = RequestMethod.GET)
    @PageableDefaults(sort = {"parentIds=asc", "weight=asc"})
    public String listTable(
            HttpServletRequest request,
            @PathVariable("parent") M parent,
            Searchable searchable, Model model) throws UnsupportedEncodingException {

        list(request, parent, searchable, model);
        return viewName("listChildrenTable");

    }
    
    /**
     * 批量删除操作
     * @author JeeLearner
     * @date 2018年4月19日
     * @param ids
     * @param backURL
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "batch/delete")
    public String deleteInBatch(
            @RequestParam(value = "ids", required = false) ID[] ids,
            @RequestParam(value = Constants.BACK_URL, required = false) String backURL,
            RedirectAttributes redirectAttributes) {


        if (permissionList != null) {
            permissionList.assertHasDeletePermission();
        }
        
        //如果要求不严格 此处可以删除判断 前台已经判断过了
        Searchable searchable = Searchable.newSearchable().addSearchFilter("id", SearchOperator.in, ids);
        List<M> list = baseService.findAllWithNoPageNoSort(searchable);
        for (M m : list) {
			if(m.isRoot()){
				redirectAttributes.addFlashAttribute(Constants.ERROR, "您删除的数据中包含根节点，根节点不能删除");
                return redirectToUrl(backURL);
			}
		}
        baseService.deleteSelfAndChild(list);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "删除成功");
        return redirectToUrl(backURL);
    }
    
    @RequestMapping(value = "success")
    public String success() {
        return viewName("success");
    }
    //TODO ///////////////////////////////////ajax///////////////////////////////////////////////

    /**
     * 根据名称异步即时查询资源
     * @author JeeLearner
     * @date 2018年4月10日
     * @param searchable
     * @param term
     * @param excludeId
     * @return
     */
    @RequestMapping("ajax/autocomplete")
    @PageableDefaults(value = 30)
    @ResponseBody
    public Set<String> autocomplete(
            Searchable searchable,
            @RequestParam("term") String term,
            @RequestParam(value = "excludeId", required = false) ID excludeId) {
    	term = TypeToUtil.byteToUTF(term);
        return baseService.findNames(searchable, term, excludeId);
    }
    
    /**
     * 按下回车键，查询资源
     * @author JeeLearner
     * @date 2018年4月10日
     * @param request
     * @param async
     * @param asyncLoadAll
     * @param searchName
     * @param parentId
     * @param excludeId
     * @param onlyCheckLeaf
     * @param searchable
     * @return
     */
    @RequestMapping(value = "ajax/load")
    @PageableDefaults(sort = {"parentIds=asc", "weight=asc"})
    @ResponseBody
    public Object load(
            HttpServletRequest request,
            @RequestParam(value = "async", defaultValue = "true") boolean async,
            @RequestParam(value = "asyncLoadAll", defaultValue = "false") boolean asyncLoadAll,
            @RequestParam(value = "searchName", required = false) String searchName,
            @RequestParam(value = "id", required = false) ID parentId,
            @RequestParam(value = "excludeId", required = false) ID excludeId,
            @RequestParam(value = "onlyCheckLeaf", required = false, defaultValue = "false") boolean onlyCheckLeaf,
            Searchable searchable) {

    	searchName = TypeToUtil.byteToUTF(searchName);
        M excludeM = baseService.findOne(excludeId);

        List<M> models = null;
        
        if(!StringUtils.isEmpty(searchName)){ //按name模糊查询
        	searchable.addSearchParam("name_like", searchName);
        	models = baseService.findAllByName(searchable, excludeM);
        	if(!async || asyncLoadAll){ //非异步模式 查自己及子子孙孙 但排除
        		searchable.removeSearchFilter("name_like");
        		List<M> children = baseService.findChildren(models, searchable);
        		models.removeAll(children);
        		models.addAll(children);
        	} else { //异步模式 只查匹配的一级
        		
        	}
        } else { //没有输入name,根据有没有parentId加载
        	if(parentId != null){ //只查某个节点下的 异步
        		searchable.addSearchFilter("parentId", SearchOperator.eq, parentId);
        	}
        	//TODO 不太清楚
        	if (async && !asyncLoadAll) { //异步模式下 且非异步加载所有
                //排除自己 及 子子孙孙
                baseService.addExcludeSearchFilter(searchable, excludeM);
            }
        	
        	if (parentId == null && !asyncLoadAll) {
                models = baseService.findRootAndChild(searchable);
            } else {
                models = baseService.findAllWithSort(searchable);
            }
        }
        return convertToZtreeList(
                request.getContextPath(),
                models,
                async && !asyncLoadAll && parentId != null,
                onlyCheckLeaf);
    }
    
    /**
     * 新增新节点
     * @author JeeLearner
     * @date 2018年4月11日
     * @param request
     * @param parent
     * @return
     */
    @RequestMapping(value = "ajax/{parent}/appendChild", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object ajaxAppendChild(HttpServletRequest request, @PathVariable("parent") M parent) {

        if (permissionList != null) {
            permissionList.assertHasCreatePermission();
        }

    	M child = newModel();
    	child.setName("新节点");
    	baseService.appendChild(parent, child);
    	return convertToZtree(child, true, true);
    }
    
    /**
     * 删除节点
     * @author JeeLearner
     * @date 2018年4月11日
     * @param request
     * @param parent
     * @return
     */
    @RequestMapping(value = "ajax/{id}/delete", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object ajaxDeleteSelfAndChildren(@PathVariable("id") ID id) {


        if (this.permissionList != null) {
            this.permissionList.assertHasEditPermission();
        }

        M tree = baseService.findOne(id);
        baseService.deleteSelfAndChild(tree);
        return convertToZtree(tree, true, true);
    }
    
    /**
     * 节点重命名
     * @author JeeLearner
     * @date 2018年4月11日
     * @param id
     * @param newName
     * @return
     */
    @RequestMapping(value = "ajax/{id}/rename", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object ajaxRename(@PathVariable("id") M tree,@RequestParam("newName") String newName) {

        if (this.permissionList != null) {
            this.permissionList.assertHasEditPermission();
        }
        newName = TypeToUtil.byteToUTF(newName);
        tree.setName(newName);
        baseService.update(tree);
        return convertToZtree(tree, true, true);
    }
    
    /**
     * 移动节点
     * @author JeeLearner
     * @date 2018年4月11日
     * @param source
     * @param target
     * @param moveType
     * @return
     */
    @RequestMapping(value = "ajax/{sourceId}/{targetId}/{moveType}/move", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object ajaxRename(@PathVariable("sourceId") M source,
    		@PathVariable("targetId") M target,
    		@PathVariable("moveType") String moveType) {

        if (this.permissionList != null) {
            this.permissionList.assertHasEditPermission();
        }
        baseService.move(source, target, moveType);
        
        //return target; //返回哪个都可以
        return source;
    }
    
    
    /**
     * 将数据转为ZTree类型数据
     * @author JeeLearner
     * @date 2018年4月10日
     * @param contextPath
     * @param models
     * @param async
     * @param onlySelectLeaf
     * @return
     */
    private List<ZTree<ID>> convertToZtreeList(String contextPath, List<M> models, boolean async, boolean onlySelectLeaf){
    	List<ZTree<ID>> zTrees = Lists.newArrayList();

        if (models == null || models.isEmpty()) {
            return zTrees;
        }
        
        for (M m : models) {
        	ZTree zTree = convertToZtree(m, !async, onlySelectLeaf);
            zTrees.add(zTree);
		}
        return zTrees;
    }
    
    private ZTree convertToZtree(M m, boolean open, boolean onlyCheckLeaf){
    	ZTree<ID> zTree = new ZTree<ID>();
    	zTree.setId(m.getId());
        zTree.setpId(m.getParentId());
        zTree.setName(m.getName());
        zTree.setIconSkin(m.getIcon());
        zTree.setOpen(open);
        zTree.setRoot(m.isRoot());
        zTree.setIsParent(m.isHasChildren());

        if (onlyCheckLeaf && zTree.isIsParent()) {
            zTree.setNocheck(true);
        } else {
            zTree.setNocheck(false);
        }

    	return zTree;
    }
}
