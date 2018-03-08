package com.learn.common.sys.user.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.learn.common.jdbc.jpa.dao.support.annotation.EnableQueryCache;
import com.learn.common.jdbc.jpa.entity.BaseEntity;

@Entity
@Table(name = "sys_user_organization_job")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserOrganizationJob extends BaseEntity<Long>{

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private User user;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "job_id")
    private Long jobId;

    
	public UserOrganizationJob() {
	}
	public UserOrganizationJob(Long id) {
        setId(id);
    }
	public UserOrganizationJob(Long organizationId, Long jobId) {
		this.organizationId = organizationId;
		this.jobId = jobId;
	}
	
	// ---------getter/setter
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	@Override
	public String toString() {
		return "UserOrganizationJob [user=" + user + ", organizationId=" + organizationId + ", jobId=" + jobId + "]";
	}
    
}
