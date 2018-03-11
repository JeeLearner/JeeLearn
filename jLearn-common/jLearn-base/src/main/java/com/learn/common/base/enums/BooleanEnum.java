package com.learn.common.base.enums;

/**
 * 枚举   是/否
 * @author JeeLearner
 * @date 2018年3月5日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public enum BooleanEnum {
    TRUE(Boolean.TRUE, "是"), FALSE(Boolean.FALSE, "否");

    private final Boolean value;
    private final String info;

    private BooleanEnum(Boolean value, String info) {
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
