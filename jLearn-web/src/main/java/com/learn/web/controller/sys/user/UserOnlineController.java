package com.learn.web.controller.sys.user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.mgt.OnlineSession;
import org.apache.shiro.session.mgt.OnlineSession.OnlineStatus;
import org.apache.shiro.session.mgt.eis.OnlineSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.common.base.utils.MessageUtils;
import com.learn.common.jdbc.jpa.entity.BaseEntity;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.sys.user.entity.UserOnline;
import com.learn.web.support.BaseCRUDController;

/**
 * 在线用户控制器
 * 
 * @author JeeLearner
 * @date 2018年4月2日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller
@RequestMapping(value = "/admin/sys/user/online")
public class UserOnlineController extends BaseCRUDController<UserOnline, String> {

	@Autowired
	private OnlineSessionDAO onlineSessionDAO;

	@Override
	public String list(Model model, Searchable searchable) {
		if (!SecurityUtils.getSubject().isPermitted("sys:userOnline:view or monitor:userOnline:view")) {
			throw new UnauthorizedException(
					MessageUtils.message("no.view.permission", "sys:userOnline:view或monitor:userOnline:view"));
		}
		return super.list(model, searchable);
	}

	@RequestMapping("/forceLogout")
	public String forceLogout(@RequestParam(value = "ids") String[] ids) {
		if (!SecurityUtils.getSubject().isPermitted("sys:userOnline or monitor:userOnline")) {
			throw new UnauthorizedException(
					MessageUtils.message("no.view.permission", "sys:userOnline或monitor:userOnline"));
		}

		for (String id : ids) {
			UserOnline online = baseService.findOne(id);
			if (online == null) {
				continue;
			}
			OnlineSession onlineSession = (OnlineSession)onlineSessionDAO.readSession(online.getId());
			if (onlineSession == null) {
                continue;
            }
			onlineSession.setStatus(OnlineSession.OnlineStatus.force_logout);
			online.setStatus(OnlineStatus.force_logout);
			baseService.update(online);
		}
		return redirectToUrl(null);
	}

}
