package com.learn.common.service.maintain.push;

import org.springframework.web.context.request.async.DeferredResult;

public interface PushService {

	/**
	 * 用户是否第一次来队列
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 * @return
	 */
	public boolean isOnline(final Long userId);
	/**
	 * 上线后 创建一个空队列，防止多次判断
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 */
	public void online(final Long userId);
	
	public DeferredResult newDeferredResult(final Long userId);
	/**
	 * 推送消息、通知
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 * @param data
	 */
	public void push(final Long userId, final Object data);
	/**
	 * 离线用户，即清空用户的DefferedResult 这样就是新用户，可以即时得到通知
     *
     * 比如刷新主页时，需要offline
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param userId
	 */
	public void offline(final Long userId);
}
