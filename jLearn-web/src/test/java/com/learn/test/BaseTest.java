package com.learn.test;

import java.nio.file.attribute.UserPrincipalLookupService;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.learn.common.service.sys.user.UserService;
import com.learn.common.sys.user.dao.UserDao;
import com.learn.common.sys.user.entity.User;

@ContextConfiguration({"classpath:spring-config.xml"})
//@Transactional(transactionManager = "transactionManager")
//@Rollback(value = true)
@RunWith(SpringJUnit4ClassRunner.class)  
public class BaseTest{

	//@Autowired
	//private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void test1(){
		User user = userDao.findByUsername("showcase");
		
		System.out.println(user);
		Assert.assertEquals("showcase@sishuok.com", user.getEmail());
		//System.out.println(user);
	}
	
	@Test
	public void test(){
		//User user = userService.findByUsername("showcase");
		//System.out.println(userService);
		//System.out.println(user);
	}
 
}

