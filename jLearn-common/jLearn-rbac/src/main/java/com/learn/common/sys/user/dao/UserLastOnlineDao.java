package com.learn.common.sys.user.dao;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.common.sys.user.entity.UserLastOnline;

/**
 * 用户最后在线session 数据层
 * @author JeeLearner
 * @date 2018年3月13日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface UserLastOnlineDao extends BaseRepository<UserLastOnline, Long> {

    UserLastOnline findByUserId(Long userId);
}
