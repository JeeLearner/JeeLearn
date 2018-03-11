package com.learn.common.sys.group.entity;

/**
 * 用户组类型
 * @author JeeLearner
 * @date 2018年3月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public enum GroupType {

    user("用户组"), organization("组织机构组");

    private final String info;

    private GroupType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
