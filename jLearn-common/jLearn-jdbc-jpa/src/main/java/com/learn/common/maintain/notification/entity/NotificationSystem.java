package com.learn.common.maintain.notification.entity;

/**
 * 触发的子系统
 * @author JeeLearner
 * @date 2018年4月8日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public enum NotificationSystem {

    system("系统"), excel("excel");

    private final String info;

    private NotificationSystem(final String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
