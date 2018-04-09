package com.learn.common.maintain.notification.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.learn.common.jdbc.jpa.entity.BaseEntity;

/**
 * 通知模板实体类
 * @author JeeLearner
 * @date 2018年4月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "maintain_notification_template")
public class NotificationTemplate extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234119496112492402L;

	/**
     * 模板名称 必须唯一 发送时使用
     */
    @NotNull(message = "{not.null}")
    @Length(min=1, max=100, message = "{length.not.valid}")
    private String name;

    /**
     * 所属系统
     */
    @NotNull(message = "{not.null}")
    @Enumerated(EnumType.STRING)
    private NotificationSystem system;


    /**
     * 模板标题
     */
    @Length(min=1, max=200, message = "{length.not.valid}")
    private String title;


    /**
     * 模板内容
     */
    private String template;

    /**
     * 是否已逻辑删除
     */
    private Boolean deleted = Boolean.FALSE;

    /**
     * 标记为逻辑删除
     * @author JeeLearner
     * @date 2018年4月9日
     */
    public void markDeleted(){
    	setDeleted(Boolean.TRUE);
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
