package com.learn.web.controller.sys.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.common.base.enums.BooleanEnum;
import com.learn.common.service.sys.user.UserService;
import com.learn.common.sys.user.entity.User;
import com.learn.common.sys.user.entity.UserStatus;
import com.learn.web.support.BaseCRUDController;

@Controller("adminUserController")
@RequestMapping(value = "/admin/sys/user")
public class UserController extends BaseCRUDController<User, Long> {

	@Autowired
	private UserService userService;
	
	
	
	
	
	
	public UserController() {
		setResourceIdentity("sys:user");
	}




	@Override
    protected void setCommonData(Model model) {
        model.addAttribute("statusList", UserStatus.values());
        model.addAttribute("booleanList", BooleanEnum.values());
    }

	@RequestMapping("a")
	public String testService(){
		//User user = userService.findByUsername("showcase");
		//User user = userService.findByUsername("show");
		//User user = userService.findByEmail("showcase@sishuok.com");
		
		return "front/login";
		
	}
}
