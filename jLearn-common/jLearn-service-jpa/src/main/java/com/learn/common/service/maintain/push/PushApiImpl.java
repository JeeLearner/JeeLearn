package com.learn.common.service.maintain.push;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 消息推送API
 * @author JeeLearner
 * @date 2018年4月8日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Service
public class PushApiImpl implements PushApi{

	@Autowired
    private PushService pushService;
	
	@Override
	public void pushUnreadMessage(Long userId, Long unreadMessageCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushNewNotification(Long userId, List<Map<String, Object>> notifiations) {
		Map<String, Object> data = Maps.newHashMap();
        data.put("notifications", notifiations);
        pushService.push(userId, data);
	}

	@Override
	public void offline(Long userId) {
		pushService.offline(userId);
	}

}
