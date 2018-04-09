package com.learn.common.maintain.notification.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.learn.common.jdbc.jpa.entity.BaseEntity;

/**
 * 通知数据
 * @author JeeLearner
 * @date 2018年4月8日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "maintain_notification_data")
public class NotificationData extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6575659065791833659L;

	/**
     *  接收通知的用户
     */
    @NotNull(message = "{not.null}")
    @Column(name = "user_id")
    private Long userId;
    
    /**
     * 通知所属系统
     */
    @NotNull(message = "{not.null}")
    @Enumerated(EnumType.STRING)
    private NotificationSystem system;

    private String title;
    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知时间
     */
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    /**
     * 是否已读
     */
    @Column(name = "is_read")
    private Boolean read = Boolean.FALSE;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public NotificationSystem getSystem() {
		return system;
	}

	public void setSystem(NotificationSystem system) {
		this.system = system;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}
    
    
    
}
