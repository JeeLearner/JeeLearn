package com.learn.common.jdbc.jpa.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.common.jdbc.jpa.support.BaseUserTest;
import com.learn.common.jdbc.jpa.support.entity.User;

/**
 * UserDao集成测试
 * <p>测试时使用内嵌的HSQL内存数据库完成</p>
 * @author JeeLearner
 * @date 2018年3月6日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试 
public class CRUDUserDaoTest extends BaseUserTest{

	@PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    private User user;

    @Before
    public void setUp() {
        user = createUser();
    }

    @After
    public void tearDown() {
        user = null;
    }
    
    @Test
    public void testSave() {
        User dbUser = userDao.save(user);
        assertNotNull(dbUser.getId());
    }
}
