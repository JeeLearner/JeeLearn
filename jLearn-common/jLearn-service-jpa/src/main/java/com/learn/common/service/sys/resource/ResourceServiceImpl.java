package com.learn.common.service.sys.resource;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.entity.search.SearchOperator;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.plugin.service.BaseTreeableService;
import com.learn.common.service.sys.auth.UserAuthService;
import com.learn.common.sys.resource.entity.Resource;
import com.learn.common.sys.resource.entity.tmp.Menu;
import com.learn.common.sys.user.entity.User;

@Service
public class ResourceServiceImpl extends BaseTreeableService<Resource, Long> implements ResourceService {

	@Autowired
	private UserAuthService userAuthService;

	@Override
	public String findActualResourceIdentity(Resource resource) {

		if (resource == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder(resource.getIdentity());

		boolean hasResourceIdentity = !StringUtils.isEmpty(resource.getIdentity());

		Resource parent = findOne(resource.getParentId());
		while (parent != null) {
			if (!StringUtils.isEmpty(parent.getIdentity())) {
				sb.insert(0, parent.getIdentity() + ":");
				hasResourceIdentity = true;
			}
			parent = findOne(parent.getParentId());
		}

		// 如果用户没有声明 资源标识 且父也没有，那么就为空
		if (!hasResourceIdentity) {
			return "";
		}

		// 如果最后一个字符是: 因为不需要，所以删除之
		int length = sb.length();
		if (length > 0 && sb.lastIndexOf(":") == length - 1) {
			sb.deleteCharAt(length - 1);
		}

		// 如果有儿子 最后拼一个*
		boolean hasChildren = false;
		for (Resource r : findAll()) {
			if (resource.getId().equals(r.getParentId())) {
				hasChildren = true;
				break;
			}
		}
		if (hasChildren) {
			sb.append(":*");
		}

		return sb.toString();
	}

	@Override
	public List<Menu> findMenus(User user) {
		Searchable searchable = Searchable.newSearchable().addSearchFilter("show", SearchOperator.eq, true)
				.addSort(new Sort(Sort.Direction.DESC, "parentId", "weight"));

		List<Resource> resources = findAllWithSort(searchable);

		Set<String> userPermissions = userAuthService.findStringPermissions(user);

		Iterator<Resource> iterator = resources.iterator();
		while (iterator.hasNext()) {
			if (!hasPermission(iterator.next(), userPermissions)) {
				iterator.remove();
			}

		}
		return convertToMenus(resources);
	}

	private boolean hasPermission(Resource resource, Set<String> userPermissions) {
		String actualResourceIdentity = findActualResourceIdentity(resource);
		if (StringUtils.isEmpty(actualResourceIdentity)) {
			return true;
		}

		for (String permission : userPermissions) {
			if (hasPermission(permission, actualResourceIdentity)) {
				return true;
			}
		}

		return false;
	}

	private boolean hasPermission(String permission, String actualResourceIdentity) {

		// 得到权限字符串中的 资源部分，如a:b:create --->资源是a:b
		String permissionResourceIdentity = permission.substring(0, permission.lastIndexOf(":"));

		// 如果权限字符串中的资源 是 以资源为前缀 则有权限 如a:b 具有a:b的权限
		if (permissionResourceIdentity.startsWith(actualResourceIdentity)) {
			return true;
		}

		// 模式匹配
		WildcardPermission p1 = new WildcardPermission(permissionResourceIdentity);
		WildcardPermission p2 = new WildcardPermission(actualResourceIdentity);

		return p1.implies(p2) || p2.implies(p1);
	}

	@SuppressWarnings("unchecked")
	public static List<Menu> convertToMenus(List<Resource> resources) {

		if (resources.size() == 0) {
			return Collections.EMPTY_LIST;
		}

		//Resource remove = resources.remove(resources.size() - 1);
		Menu root = convertToMenu(resources.remove(resources.size() - 1));

		recursiveMenu(root, resources);
		List<Menu> menus = root.getChildren();
		removeNoLeafMenu(menus);
		
		return menus;
	}
	
	private static void removeNoLeafMenu(List<Menu> menus) {
		if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
        	Menu m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && StringUtils.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
	}

	private static void recursiveMenu(Menu menu, List<Resource> resources) {
		for (int i = resources.size() - 1; i >= 0; i--) {
			Resource resource = resources.get(i);
			if (resource.getParentId().equals(menu.getId())) {
				menu.getChildren().add(convertToMenu(resource));
				resources.remove(i);
			}
		}

		for (Menu subMenu : menu.getChildren()) {
			recursiveMenu(subMenu, resources);
		}
	}

	private static Menu convertToMenu(Resource resource) {
		return new Menu(resource.getId(), resource.getName(), resource.getIcon(), resource.getUrl());
	}
}
