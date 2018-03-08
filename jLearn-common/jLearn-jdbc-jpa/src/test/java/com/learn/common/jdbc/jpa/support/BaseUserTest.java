package com.learn.common.jdbc.jpa.support;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.common.jdbc.jpa.support.entity.BaseInfo;
import com.learn.common.jdbc.jpa.support.entity.SchoolInfo;
import com.learn.common.jdbc.jpa.support.entity.SchoolType;
import com.learn.common.jdbc.jpa.support.entity.Sex;
import com.learn.common.jdbc.jpa.support.entity.User;

public abstract class BaseUserTest extends BaseTest{

	public User createUser() {
        User user = new User();
        user.setUsername("jeeLearn$$$" + System.nanoTime() + RandomStringUtils.random(10));
        user.setPassword("123456");
        user.setRegisterDate(new Date());
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setRealname("zhangkaitao");
        baseInfo.setSex(Sex.male);
        baseInfo.setBirthday(new Timestamp(System.currentTimeMillis()));
        baseInfo.setAge(15);
        user.setBaseInfo(baseInfo);

        SchoolInfo primary = new SchoolInfo();
        primary.setName("abc");
        primary.setType(SchoolType.primary_school);
        user.addSchoolInfo(primary);

        SchoolInfo secondary = new SchoolInfo();
        secondary.setName("bcd");
        secondary.setType(SchoolType.secondary_school);
        user.addSchoolInfo(secondary);

        SchoolInfo high = new SchoolInfo();
        high.setName("cde");
        high.setType(SchoolType.high_school);
        user.addSchoolInfo(high);

        SchoolInfo university = new SchoolInfo();
        university.setName("def");
        university.setType(SchoolType.university);
        user.addSchoolInfo(university);

        return user;
    }
}
