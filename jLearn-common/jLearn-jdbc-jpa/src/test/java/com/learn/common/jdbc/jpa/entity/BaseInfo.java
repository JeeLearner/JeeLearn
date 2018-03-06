package com.learn.common.jdbc.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户基本信息
 * @author lyd
 * @date 2018年3月6日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "user_baseinfo")
public class BaseInfo extends BaseEntity<Long> {

    @OneToOne()
    @JoinColumn(name = "user_id", unique = true, nullable = false, updatable = false)
    private User user;

    /**
     * 真实名称
     */
    @Column(name = "realname", length = 100)
    private String realname;

    /**
     * 性别
     */
    @Column(name = "sex", length = 2)
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "age")
    private int age;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}