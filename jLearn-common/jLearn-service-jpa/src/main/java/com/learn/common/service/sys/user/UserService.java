package com.learn.common.service.sys.user;

import com.learn.common.sys.user.entity.User;
import com.learn.common.sys.user.entity.UserStatus;

public interface UserService{

	public User save(User user);
	
	public User update(User user);
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	public User findByMobilePhoneNumber(String mobilePhoneNumber);
	
	public User changePassword(User user, String newPassword);
	
	public User changeStatus(User opUser, User user, UserStatus newStatus, String reason);
	/*
	 * 登录
	 */
	public User login(String username, String password);
}
