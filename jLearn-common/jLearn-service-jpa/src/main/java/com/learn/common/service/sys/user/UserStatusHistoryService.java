package com.learn.common.service.sys.user;

import com.learn.common.sys.user.entity.User;
import com.learn.common.sys.user.entity.UserStatus;
import com.learn.common.sys.user.entity.UserStatusHistory;

/**
 * 用户状态历史信息 服务层
 * @author lyd
 * @date 2018年3月7日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface UserStatusHistoryService {

	public void log(User opUser, User user, UserStatus newStatus, String reason);
	
	public UserStatusHistory findLastHistory(User user);
	
	public String getLastReason(User user);
}
