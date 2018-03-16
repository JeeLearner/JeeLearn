package com.learn.common.sys.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.sys.user.dao.UserOnlineDao;
import com.learn.common.sys.user.entity.UserOnline;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author JeeLearner
 * @date 2018年3月13日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Service
public class UserOnlineServiceImpl extends BaseService<UserOnline, String> implements UserOnlineService {

	@Autowired
	private UserOnlineDao userOnlineDao;

	/**
	 * 上线
	 *
	 * @param userOnline
	 */
	public void online(UserOnline userOnline) {
		save(userOnline);
	}

	/**
	 * 下线
	 *
	 * @param sid
	 */
	public void offline(String sid) {
		UserOnline userOnline = findOne(sid);
		if (userOnline != null) {
			delete(userOnline);
		}
		// 游客 无需记录上次访问记录
		// 此处使用数据库的触发器完成同步
		// if(userOnline.getUserId() == null) {
		// userLastOnlineService.lastOnline(UserLastOnline.fromUserOnline(userOnline));
		// }
	}

	/**
	 * 批量下线
	 *
	 * @param needOfflineIdList
	 */
	public void batchOffline(List<String> needOfflineIdList) {
		userOnlineDao.batchDelete(needOfflineIdList);
	}

	/**
	 * 无效的UserOnline
	 *
	 * @return
	 */
	public Page<UserOnline> findExpiredUserOnlineList(Date expiredDate, Pageable pageable) {
		return userOnlineDao.findExpiredUserOnlineList(expiredDate, pageable);
	}

}
