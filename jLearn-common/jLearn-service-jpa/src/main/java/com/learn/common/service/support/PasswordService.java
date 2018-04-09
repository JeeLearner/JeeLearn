package com.learn.common.service.support;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learn.common.base.utils.security.Md5Utils;
import com.learn.common.base.utils.web.UserLogUtils;
import com.learn.common.exception.sys.user.UserPasswordNotMatchException;
import com.learn.common.exception.sys.user.UserPasswordRetryLimitExceedException;
import com.learn.common.sys.user.entity.User;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 用户密码服务
 * @author JeeLearner
 * @date 2018年3月7日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Service
public class PasswordService {

	@Autowired
    private CacheManager ehcacheManager;

    private Cache loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount = 10;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheManager.getCache("loginRecordCache");
    }
    
    /**
     * 用户密码验证
     * @author JeeLearner
     * @date 2018年3月7日
     * @param user
     * @param password
     */
    public void validate(User user, String password){
    	String username = user.getUsername();

        int retryCount = 0;
        
        Element cacheElement = loginRecordCache.get(username);
        if(cacheElement != null){
        	retryCount = (Integer)cacheElement.getObjectValue();
        	retryCount = (Integer) cacheElement.getObjectValue();
            if (retryCount >= maxRetryCount) {
                UserLogUtils.log(
                        username,
                        "passwordError",
                        "password error, retry limit exceed! password: {},max retry count {}",
                        password, maxRetryCount);
                throw new UserPasswordRetryLimitExceedException(maxRetryCount);
            }
        }
        
        if (!matches(user, password)) {
            loginRecordCache.put(new Element(username, ++retryCount));
            UserLogUtils.log(
                    username,
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(username);
        }
        
    }
    
    public boolean matches(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }
    
    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }
    
    
    public String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }
    
    public static void main(String[] args) {
        System.out.println(new PasswordService().encryptPassword("monitor", "123456", "iY71e4d123"));
        System.out.println("e1549e68ad21fe888ae36ec4965116cd");
    }
    
}
