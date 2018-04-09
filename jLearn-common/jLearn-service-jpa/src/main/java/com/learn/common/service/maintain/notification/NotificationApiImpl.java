package com.learn.common.service.maintain.notification;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.learn.common.base.utils.web.taglib.PrettyTimeUtils;
import com.learn.common.exception.maintain.notification.TemplateNotFoundException;
import com.learn.common.jdbc.jpa.entity.search.SearchOperator;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.maintain.notification.entity.NotificationData;
import com.learn.common.maintain.notification.entity.NotificationTemplate;
import com.learn.common.service.maintain.push.PushApi;

@Service
public class NotificationApiImpl implements NotificationApi {
	
	@Autowired
    private NotificationService notificationService;
	
	@Autowired
    private PushApi pushApi;
	
	@Autowired
    private NotificationTemplateService notificationTemplateService;
	
	@Async
	@Override
	public void notify(Long userId, String templateName, Map<String, Object> context) throws TemplateNotFoundException {
		
		NotificationTemplate template = notificationTemplateService.findByName(templateName);
        if(template == null) {
            throw new TemplateNotFoundException(templateName);
        }
		
        NotificationData data = new NotificationData();
        data.setUserId(userId);
        data.setSystem(template.getSystem());
        data.setDate(new Date());
        
        String content = template.getTemplate();
        String title = template.getTitle();
        if(content != null){
        	for (String key : context.keySet()) {
        		//TODO 如果量大可能有性能问题 需要调优
        		title = title.replace("{" + key + "}", String.valueOf(context.get(key)));
                content = content.replace("{" + key + "}", String.valueOf(context.get(key)));
			}
        }
        data.setTitle(title);
        data.setContent(content);

        notificationService.save(data);
        //推送新通知
        pushApi.pushNewNotification(userId, topFiveNotification(userId));
	}
	
	@Override
    public List<Map<String, Object>> topFiveNotification(final Long userId) {
		List<Map<String, Object>> dataList = Lists.newArrayList();

        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchFilter("userId", SearchOperator.eq, userId);
        //searchable.addSearchFilter("read", SearchOperator.eq, Boolean.FALSE);
        searchable.addSort(Sort.Direction.DESC, "id");
        searchable.setPage(0, 5);
        
        Page<NotificationData> page = notificationService.findAll(searchable);

        for (NotificationData data : page) {
        	Map<String, Object> map = Maps.newHashMap();
        	map.put("id", data.getId());
            map.put("title", data.getTitle());
            map.put("content", data.getContent());
            map.put("read", data.getRead());
            map.put("date", PrettyTimeUtils.prettyTime(data.getDate()));
            dataList.add(map);
		}
		return dataList;
	}
}
