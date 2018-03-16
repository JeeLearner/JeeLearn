package com.learn.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 * 
 * @author JeeLearner
 * @date 2018年3月15日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

	@RequestMapping(value = { "/{index:index;?.*}" }) // spring3.2.2 bug see
	public String index(Model model) {

		return "admin/index/index";
	}
}