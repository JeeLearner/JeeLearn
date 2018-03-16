package com.learn.common.sys.user.service;

import com.learn.common.sys.user.entity.UserLastOnline;

public interface UserLastOnlineService {

	/*
     * 根据userid查询上次在线信息
     */
	 public UserLastOnline findByUserId(Long userId);

    /*
     * 将上次在线信息同步到数据库
     */
	 public void lastOnline(UserLastOnline lastOnline);
}
