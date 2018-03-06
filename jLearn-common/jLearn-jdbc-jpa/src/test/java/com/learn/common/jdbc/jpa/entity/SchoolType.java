package com.learn.common.jdbc.jpa.entity;

/**
 * 学校类型
 * @author lyd
 * @date 2018年3月6日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public enum SchoolType {
    primary_school("小学"), secondary_school("中学"), high_school("高中"), university("大学");

    private final String info;

    private SchoolType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}