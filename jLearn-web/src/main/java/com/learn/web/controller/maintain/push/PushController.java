package com.learn.web.controller.maintain.push;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.learn.common.service.maintain.notification.NotificationApi;
import com.learn.common.service.maintain.push.PushApi;
import com.learn.common.service.maintain.push.PushService;
import com.learn.common.sys.user.entity.User;
import com.learn.web.extra.bind.annotation.CurrentUser;

/**
 * 实时推送用户：消息和通知
 * @author JeeLearner
 * @date 2018年4月8日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller
public class PushController {

	@Autowired
	private PushService pushService;
	
	@Autowired
    private NotificationApi notificationApi;
	
	@RequestMapping(value = "/admin/polling")
	@ResponseBody
	public Object polling(@CurrentUser User user, HttpServletResponse resp){
		resp.setHeader("Connection", "Keep-Alive");
        resp.addHeader("Cache-Control", "private");
        resp.addHeader("Pragma", "no-cache");

        Long userId = user.getId();
        if(userId == null) {
            return null;
        }
        //如果用户第一次来 立即返回
        if(!pushService.isOnline(userId)){
        	//TODO 调用消息和通知数据
        	
        	List<Map<String, Object>> notifications = notificationApi.topFiveNotification(userId);
        	
        	Map<String, Object> data = Maps.newHashMap();
        	//data.put("unreadMessageCount", unreadMessageCount);
            data.put("notifications", notifications);
            data.put("unreadMessageCount", 1);
            pushService.online(userId);
            return data;
        } else {
        	//长轮询
        	return pushService.newDeferredResult(userId);
        }
	}
}
