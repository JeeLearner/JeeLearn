package com.learn.common.jdbc.jpa.support.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.learn.common.jdbc.jpa.entity.BaseEntity;

@Entity
@Table(name = "user")
public class User extends BaseEntity<Long> {
	
	@Column(name = "username", unique = true, length = 200)
    private String username;

    @Column(name = "password", length = 200)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "registerDate")
    private Date registerDate;
    
    /**
     * 基本信息
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private BaseInfo baseInfo;

    /**
     * 学校信息
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<SchoolInfo> schoolInfoSet;

    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public BaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
		this.baseInfo.setUser(this);
	}

	public Set<SchoolInfo> getSchoolInfoSet() {
		if(schoolInfoSet == null){
			schoolInfoSet = new HashSet<SchoolInfo>();
		}
		return schoolInfoSet;
	}

	public void setSchoolInfoSet(Set<SchoolInfo> schoolInfoSet) {
		this.schoolInfoSet = schoolInfoSet;
	}
    
	public void addSchoolInfo(SchoolInfo schoolInfo) {
        this.getSchoolInfoSet().add(schoolInfo);
        schoolInfo.setUser(this);
    }
}