package com.learn.common.sys.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.learn.common.sys.user.entity.UserOnline;

public interface UserOnlineService {

	/*
     * 上线
     */
    public void online(UserOnline userOnline);

    /*
     * 下线
     */
    public void offline(String sid);

    /*
     * 批量下线
     */
    public void batchOffline(List<String> needOfflineIdList);

    /*
     * 无效的UserOnline
     */
    public Page<UserOnline> findExpiredUserOnlineList(Date expiredDate, Pageable pageable);
    
    public UserOnline findOne(String id);

}
