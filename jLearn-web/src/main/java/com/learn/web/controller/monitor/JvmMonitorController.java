package com.learn.web.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.web.support.BaseController;

/**
 * JVM监控
 * @author JeeLearner
 * @date 2018年3月20日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller
@RequestMapping("/admin/monitor/jvm")
@RequiresPermissions("monitor:jvm:*")
public class JvmMonitorController extends BaseController {

    @RequestMapping("")
    public String index() {
        return viewName("index");
    }


}