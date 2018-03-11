package com.learn.common.sys.auth.entity;

/**
 * 授权类型
 * @author JeeLearner
 * @date 2018年3月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public enum AuthType {

    user("用户"), user_group("用户组"), organization_job("组织机构和工作职务"), organization_group("组织机构组");

    private final String info;

    private AuthType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
