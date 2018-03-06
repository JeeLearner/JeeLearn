package com.learn.common.jdbc.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 学校信息
 * @author lyd
 * @date 2018年3月6日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "user_schoolinfo")
public class SchoolInfo extends BaseEntity<Long> {

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * 学校名称
     */
    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "type", length = 2)
    @Enumerated(EnumType.ORDINAL)
    private SchoolType type;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchoolType getType() {
        return type;
    }

    public void setType(SchoolType type) {
        this.type = type;
    }
}