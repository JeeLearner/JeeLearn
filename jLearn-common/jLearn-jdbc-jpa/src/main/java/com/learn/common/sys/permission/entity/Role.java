package com.learn.common.sys.permission.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.learn.common.jdbc.jpa.dao.support.annotation.EnableQueryCache;
import com.learn.common.jdbc.jpa.entity.BaseEntity;

/**
 * 角色实体类
 * @author JeeLearner
 * @date 2018年3月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "sys_role")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseEntity<Long> {

	/**
     * 前端显示名称
     */
    private String name;
    /**
     * 系统中验证时使用的角色标识
     */
    private String role;

    /**
     * 详细描述
     */
    private String description;
    
    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    @Column(name = "is_show")
    private Boolean show = Boolean.FALSE;
    
    /**
     * 用户 组织机构 工作职务关联表
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = RoleResourcePermission.class, mappedBy = "role", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @Basic(optional = true, fetch = FetchType.EAGER)
//    @NotFound(action = NotFoundAction.IGNORE)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)//集合缓存
    @OrderBy
    private List<RoleResourcePermission> resourcePermissions;

    
    public void addResourcePermission(RoleResourcePermission roleResourcePermission) {
        roleResourcePermission.setRole(this);
        getResourcePermissions().add(roleResourcePermission);
    }
    
    //---------getter/setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public List<RoleResourcePermission> getResourcePermissions() {
		if(resourcePermissions == null){
			resourcePermissions = Lists.newArrayList();
		}
		return resourcePermissions;
	}

	public void setResourcePermissions(List<RoleResourcePermission> resourcePermissions) {
		this.resourcePermissions = resourcePermissions;
	}

    
    
}
