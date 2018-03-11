package com.learn.common.base.utils.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.learn.common.base.utils.IpUtils;
import com.learn.common.base.utils.LogUtils;

/**
 * 用户日志工具类
 * @author JeeLearner
 * @date 2018年3月7日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class UserLogUtils {

    private static final Logger SYS_USER_LOGGER = LoggerFactory.getLogger("jlearn-sys-user");


    private Logger getSysUserLog() {
        return SYS_USER_LOGGER;
    }

    /**
     * 记录格式 [ip][用户名][操作][错误消息]
     * <p/>
     * 注意操作如下：
     * loginError 登录失败
     * loginSuccess 登录成功
     * passwordError 密码错误
     * changePassword 修改密码
     * changeStatus 修改状态
     *
     * @param username
     * @param op
     * @param msg
     * @param args
     */
    public static void log(String username, String op, String msg, Object... args) {
        StringBuilder s = new StringBuilder();
        s.append(LogUtils.getBlock(getIp()));
        s.append(LogUtils.getBlock(username));
        s.append(LogUtils.getBlock(op));
        s.append(LogUtils.getBlock(msg));

        SYS_USER_LOGGER.info(s.toString(), args);
    }

    public static Object getIp() {
        RequestAttributes requestAttributes = null;

        try {
            RequestContextHolder.currentRequestAttributes();
        } catch (Exception e) {
            //ignore  如unit test
        }

        if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
            return IpUtils.getIpAddr(((ServletRequestAttributes) requestAttributes).getRequest());
        }

        return "unknown";

    }

}
