package com.learn.common.sys.permission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.learn.common.jdbc.jpa.dao.support.annotation.EnableQueryCache;
import com.learn.common.jdbc.jpa.entity.BaseEntity;

/**
 * 权限实体类
 * @author lyd
 * @date 2018年3月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "sys_permission")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends BaseEntity<Long> {

	/**
     * 前端显示名称
     */
    private String name;
    /**
     * 系统中验证时使用的权限标识
     */
    private String permission;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    @Column(name = "is_show")
    private Boolean show = Boolean.FALSE;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
