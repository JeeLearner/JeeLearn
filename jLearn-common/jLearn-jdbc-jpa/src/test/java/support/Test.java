package support;

import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.learn.common.sys.user.dao.UserDao;
import com.learn.common.sys.user.entity.User;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  
@ContextConfiguration({"classpath:spring-common.xml", "classpath:spring-config.xml"})
public class Test {

	@Autowired
	private UserDao userDao;
	
	@org.junit.Test
	public void testUserDao(){
		List<User> list = userDao.findAll();
		Assert.assertEquals(1, list.size());
	}
}
