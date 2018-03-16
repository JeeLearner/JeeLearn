package com.learn.common.sys.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.sys.user.dao.UserLastOnlineDao;
import com.learn.common.sys.user.entity.UserLastOnline;

/**
 * 
 * @author JeeLearner
 * @date 2018年3月13日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Service
public class UserLastOnlineServiceImpl extends BaseService<UserLastOnline, Long> {

    @Autowired
    private UserLastOnlineDao userLastOnlineDao;

    public UserLastOnline findByUserId(Long userId) {
        return userLastOnlineDao.findByUserId(userId);
    }

    public void lastOnline(UserLastOnline lastOnline) {
        UserLastOnline dbLastOnline = findByUserId(lastOnline.getUserId());

        if (dbLastOnline == null) {
            dbLastOnline = lastOnline;
        } else {
            UserLastOnline.merge(lastOnline, dbLastOnline);
        }
        dbLastOnline.incLoginCount();
        dbLastOnline.incTotalOnlineTime();
        //相对于save or update
        save(dbLastOnline);
    }
}
