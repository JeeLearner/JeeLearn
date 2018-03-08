package com.learn.web.controller.sys.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.common.service.sys.user.UserService;
import com.learn.common.sys.user.entity.User;

@Controller("adminUserController")
@RequestMapping(value = "/admin/sys/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	@ResponseBody
	public String testService(){
		User user = userService.findByUsername("showcase");
		return user.getEmail();
		
	}
}
