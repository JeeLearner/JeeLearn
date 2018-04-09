package com.learn.common.service.maintain.push;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushServiceImpl implements PushService{

	private volatile Map<Long, Queue<DeferredResult<Object>>> userIdToDeferredResultMap = new ConcurrentHashMap<>();
	
	@Override
	public boolean isOnline(final Long userId){
		return userIdToDeferredResultMap.containsKey(userId);
	}
	
	@Override
	public void online(final Long userId) {
		Queue<DeferredResult<Object>> queue = userIdToDeferredResultMap.get(userId);
		if(queue == null){
			queue = new LinkedBlockingDeque<DeferredResult<Object>>(); //如果jdk 1.7 可以换成ConcurrentLinkedQueue
			userIdToDeferredResultMap.put(userId, queue);
		}
	}
	
	public DeferredResult newDeferredResult(final Long userId) {
		final DeferredResult<Object> deferredResult = new DeferredResult<Object>();
		deferredResult.onCompletion(new Runnable() {
			@Override
			public void run() {
				Queue<DeferredResult<Object>> queue = userIdToDeferredResultMap.get(userId);
                if(queue != null) {
                    queue.remove(deferredResult);
                    deferredResult.setResult("");
                }
			}
		});
		deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                deferredResult.setErrorResult("");
            }
        });
		Queue<DeferredResult<Object>> queue = userIdToDeferredResultMap.get(userId);
        if(queue == null) {
            queue = new LinkedBlockingDeque<DeferredResult<Object>>();
            userIdToDeferredResultMap.put(userId, queue);
        }
        queue.add(deferredResult);

        return deferredResult;
	}
	
	@Override
	public void push(final Long userId, final Object data) {
        Queue<DeferredResult<Object>> queue =  userIdToDeferredResultMap.get(userId);
        if(queue == null) {
            return;
        }
        for (DeferredResult<Object> deferredResult : queue) {
			if(!deferredResult.isSetOrExpired()){
				if(!deferredResult.isSetOrExpired()) {
	                try {
	                    deferredResult.setResult(data);
	                } catch (Exception e) {
	                    queue.remove(deferredResult);
	                }
	            }
			}
		}
	}
	
	@Override
	public void offline(final Long userId) {
        Queue<DeferredResult<Object>> queue = userIdToDeferredResultMap.remove(userId);
        if(queue != null) {
            for(DeferredResult<Object> result : queue) {
                try {
                    result.setResult("");
                } catch (Exception e) {
                    //ignore
                }
            }
        }
    }

}
