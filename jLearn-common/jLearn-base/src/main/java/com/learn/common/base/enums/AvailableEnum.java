package com.learn.common.base.enums;

/**
 * 枚举    可用/不可用
 * @author JeeLearner
 * @date 2018年3月5日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public enum AvailableEnum {
    TRUE(Boolean.TRUE, "可用"), FALSE(Boolean.FALSE, "不可用");

    private final Boolean value;
    private final String info;

    private AvailableEnum(Boolean value, String info) {
        this.value = value;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public Boolean getValue() {
        return value;
    }
}
