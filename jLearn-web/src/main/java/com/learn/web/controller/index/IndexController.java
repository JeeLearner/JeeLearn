package com.learn.web.controller.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.common.base.bind.annotation.CurrentUser;
import com.learn.common.service.sys.resource.ResourceService;
import com.learn.common.sys.resource.entity.tmp.Menu;
import com.learn.common.sys.user.entity.User;

/**
 * 首页控制器
 * 
 * @author JeeLearner
 * @date 2018年3月15日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = { "/{index:index;?.*}" }) // spring3.2.2 bug see
	public String index(@CurrentUser User user, Model model) {

		List<Menu> menus = resourceService.findMenus(user);
		model.addAttribute("menus", menus);
		return "admin/index/index";
	}
	
	
	@RequestMapping(value = "/welcome")
    public String welcome(@CurrentUser User loginUser, Model model) {

        //未读消息
        //Long messageUnreadCount = messageService.countUnread(loginUser.getId());
        model.addAttribute("messageUnreadCount", 1);

        //最近3天的日历
        model.addAttribute("calendarCount", 2);

        return "admin/index/welcome";
    }
}