package com.learn.web.controller.monitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.learn.common.base.utils.web.taglib.PrettyMemoryUtils;
import com.learn.web.support.BaseController;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存监控控制器
 * 
 * @author JeeLearner
 * @date 2018年3月21日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller
@RequestMapping("/admin/monitor/ehcache")
@RequiresPermissions("monitor:ehcache:*")
public class EhcacheMonitorController extends BaseController {

	@Autowired
	private CacheManager cacheManager;

	@RequestMapping()
	public String index(Model model) {
		model.addAttribute("cacheManager", cacheManager);
		return viewName("index");
	}

	/**
	 * 获取键列表
	 * 
	 * @author JeeLearner
	 * @date 2018年3月21日
	 * @param model
	 * @param cacheName
	 * @param searchText
	 * @return
	 */
	@RequestMapping("{cacheName}/details")
	public String details(Model model, @PathVariable("cacheName") String cacheName,
			@RequestParam(value = "searchText", required = false, defaultValue = "") String searchText) {
		model.addAttribute("cacheName", cacheName);

		List<Object> showKeys = Lists.newArrayList();
		List<Object> allKeys = cacheManager.getCache(cacheName).getKeys();
		if (allKeys != null && allKeys.size() > 0) {
			for (Object key : allKeys) {
				if (key.toString().contains(searchText)) {
					showKeys.add(key);
				}
			}
		}

		model.addAttribute("keys", showKeys);
		return viewName("details");
	}

	/**
	 * 查询特定键列表详情
	 * 
	 * @author JeeLearner
	 * @date 2018年3月21日
	 * @param model
	 * @param cacheName
	 * @param key
	 * @return
	 */
	@RequestMapping("{cacheName}/{key}/details")
	public Object keyDetail(Model model, @PathVariable("cacheName") String cacheName, @PathVariable("key") String key) {
		Element element = cacheManager.getCache(cacheName).get(key);
		String dataPattern = "yyyy-MM-dd HH:mm:ss";
		Map<String, Object> data = Maps.newHashMap();
		data.put("objectValue", element.getObjectValue().toString());
		data.put("size", PrettyMemoryUtils.prettyByteSize(element.getSerializedSize()));
		data.put("hitCount", element.getHitCount());

		Date latestOfCreationAndUpdateTime = new Date(element.getLatestOfCreationAndUpdateTime());
		data.put("latestOfCreationAndUpdateTime", DateFormatUtils.format(latestOfCreationAndUpdateTime, dataPattern));
		Date lastAccessTime = new Date(element.getLastAccessTime());
		data.put("lastAccessTime", DateFormatUtils.format(lastAccessTime, dataPattern));
		if (element.getExpirationTime() == Long.MAX_VALUE) {
			data.put("expirationTime", "不过期");
		} else {
			Date expirationTime = new Date(element.getExpirationTime());
			data.put("expirationTime", DateFormatUtils.format(expirationTime, dataPattern));
		}

		data.put("timeToIdle", element.getTimeToIdle());
		data.put("timeToLive", element.getTimeToLive());
		data.put("version", element.getVersion());

		return data;
	}

	/**
	 * 删除键列表下某键信息
	 * 
	 * @author JeeLearner
	 * @date 2018年3月21日
	 * @param model
	 * @param cacheName
	 * @param key
	 * @return
	 */
	@RequestMapping("{cacheName}/{key}/delete")
	@ResponseBody
	public Object keyDetail(@PathVariable("cacheName") String cacheName, @PathVariable("key") String key) {
		Cache cache = cacheManager.getCache(cacheName);
		cache.remove(key);
		return "操作成功！";
	}

	/**
	 * 清空单一键列表
	 * @author JeeLearner
	 * @date 2018年3月21日
	 * @param cacheName
	 * @return
	 */
	@RequestMapping("{cacheName}/clear")
	@ResponseBody
	public Object clear(@PathVariable("cacheName") String cacheName) {

		Cache cache = cacheManager.getCache(cacheName);
		cache.clearStatistics();
		cache.removeAll();
		return "操作成功！";
	}
}
