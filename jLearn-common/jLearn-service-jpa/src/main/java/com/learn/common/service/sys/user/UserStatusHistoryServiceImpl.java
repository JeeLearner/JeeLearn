package com.learn.common.service.sys.user;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.sys.user.entity.User;
import com.learn.common.sys.user.entity.UserStatus;
import com.learn.common.sys.user.entity.UserStatusHistory;

@Service
public class UserStatusHistoryServiceImpl extends BaseService<UserStatusHistory, Long>
		implements UserStatusHistoryService {

	@Override
	public void log(User opUser, User user, UserStatus newStatus, String reason) {
		UserStatusHistory history = new UserStatusHistory();
		history.setUser(user);
		history.setOpUser(opUser);
		history.setOpDate(new Date());
		history.setStatus(newStatus);
		history.setReason(reason);
		save(history);
	}

	@Override
	public UserStatusHistory findLastHistory(User user) {
		Searchable searchable = Searchable.newSearchable().addSearchParam("user_eq", user)
				.addSort(Sort.Direction.DESC, "opDate").setPage(0, 1);
		Page<UserStatusHistory> page = baseRepository.findAll(searchable);
		if (page.hasContent()) {
			return page.getContent().get(0);
		}
		return null;
	}

	@Override
	public String getLastReason(User user) {
		UserStatusHistory history = findLastHistory(user);
		if (history == null) {
			return "";
		}
		return history.getReason();
	}
}
