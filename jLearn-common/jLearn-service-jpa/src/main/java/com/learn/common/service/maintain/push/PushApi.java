package com.learn.common.service.maintain.push;

import java.util.List;
import java.util.Map;

public interface PushApi {

	/**
	 * 推送未读消息
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 * @param unreadMessageCount
	 */
	public void pushUnreadMessage(final Long userId, Long unreadMessageCount);
	/**
	 * 推送未读通知
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 * @param notifiations
	 */
	public void pushNewNotification(final Long userId, List<Map<String, Object>> notifiations);
	/**
     * 离线用户，即清空用户的DefferedResult 这样就是新用户，可以即时得到通知
     * 比如刷新主页时，需要offline
     * 
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 */
	void offline(Long userId);
}
