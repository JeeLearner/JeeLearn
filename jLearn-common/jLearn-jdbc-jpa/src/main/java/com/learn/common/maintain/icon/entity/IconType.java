package com.learn.common.maintain.icon.entity;

/**
 * 图标类型
 * @author JeeLearner
 * @date 2018年4月11日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public enum IconType {
    css_class("css类图标"), 
    upload_file("文件图标"), 
    css_sprite("css精灵图标");

    private final String info;

    private IconType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
