package com.learn.web.controller.personal.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.common.jdbc.jpa.entity.search.SearchOperator;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.maintain.notification.entity.NotificationData;
import com.learn.common.service.maintain.notification.NotificationService;
import com.learn.common.sys.user.entity.User;
import com.learn.web.extra.bind.annotation.CurrentUser;
import com.learn.web.extra.bind.annotation.PageableDefaults;
import com.learn.web.support.BaseController;

/**
 * 我的通知 控制器
 * @author JeeLearner
 * @date 2018年4月8日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller
@RequestMapping("/admin/personal/notification")
public class NotificationController extends BaseController {

	@Autowired
	private NotificationService notificationService;
	
	/**
	 * 查看通知列表
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param user
	 * @param model
	 * @param pageable
	 * @return
	 */
	@RequestMapping
	@PageableDefaults(value = 20, sort = "id=desc")
	public String list(@CurrentUser User user, Model model, Pageable pageable){
		Searchable searchable = Searchable.newSearchable();
		searchable.addSearchFilter("userId", SearchOperator.eq, user.getId());
		
		Page<NotificationData> page = notificationService.findAll(pageable);
		if(pageable.getPageNumber() == 0) {
			//打开通知列表即视为此用户的所有通知为已读
            notificationService.markReadAll(user.getId());
        }
		model.addAttribute("page", page);
		return viewName("list");
	}
	
	/**
	 * 首页上查看本页单条通知即为已读
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param id
	 * @return
	 */
	@RequestMapping("/markRead")
    @ResponseBody
    public String markRead(@RequestParam("id") Long id) {
        notificationService.markRead(id);
        return "";
    }
}
