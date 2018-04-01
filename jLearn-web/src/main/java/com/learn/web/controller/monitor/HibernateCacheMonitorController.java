package com.learn.web.controller.monitor;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.Cache;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.common.jdbc.jpa.dao.hibernate.HibernateUtils;
import com.learn.web.support.BaseController;

/**
 * 系统监控-Hibernate监控 控制器
 * 
 * @author JeeLearner
 * @date 2018年3月26日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Controller
@RequestMapping("/admin/monitor/hibernate")
@RequiresPermissions("monitor:hibernate:*")
public class HibernateCacheMonitorController extends BaseController {

	@PersistenceContext
	private EntityManager em;

	@ModelAttribute
	public void setCommonData(Model model) {
		Statistics statistics = HibernateUtils.getSessionFactory(em).getStatistics();
		model.addAttribute("statistics", statistics);

		Date startDate = new Date(statistics.getStartTime());
		Date nowDate = new Date();
		long upSeconds = (nowDate.getTime() - startDate.getTime()) / 1000;
		model.addAttribute("upSeconds", upSeconds);
	}

	/**
	 * 所有信息
	 * 
	 * @author JeeLearner
	 * @date 2018年3月26日
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model) {
		setMemoryInfo(model);
		model.addAttribute("sessionFactory", HibernateUtils.getSessionFactory(em));

		Map<String, Object> properties = new TreeMap<String, Object>(em.getEntityManagerFactory().getProperties());
		model.addAttribute("properties", properties);
		return viewName("index");
	}

	/**
	 * 二级缓存统计
	 * 
	 * @author JeeLearner
	 * @date 2018年3月26日
	 * @param model
	 * @return
	 */
	@RequestMapping("/secondLevelCache")
	public String secondLevelCache(Model model) {
		setMemoryInfo(model);
		return viewName("secondLevelCache");
	}

	/**
	 * 查询缓存统计
	 * 
	 * @author JeeLearner
	 * @date 2018年3月26日
	 * @return
	 */
	@RequestMapping("/queryCache")
	public String queryCache() {
		return viewName("queryCache");
	}

	/**
	 * 实体和集合 增删改查 次数 统计
	 * @author JeeLearner
	 * @date 2018年3月26日
	 * @return
	 */
    @RequestMapping("/entityAndCollectionCRUDCount")
    public String entityAndCollectionCRUDCount() {
        return viewName("entityAndCollectionCRUDCount");
    }


    /**
     * 缓存控制
     * @return
     */
    @RequestMapping(value = "/control", method = RequestMethod.GET)
    public String showControlForm() {
        return viewName("controlForm");
    }
    
    /**
     * 失效整个二级缓存
     * @author JeeLearner
     * @date 2018年3月26日
     * @return
     */
    @RequestMapping(value = "/evictAll")
    @ResponseBody
    public String evictAll() {
        HibernateUtils.evictLevel2Cache(em);
        return "操作成功";
    }

    /**
     * 清空二级缓存，重新计算
     * @author JeeLearner
     * @date 2018年3月26日
     * @return
     */
    @RequestMapping(value = "/clearAll")
    @ResponseBody
    public String clearAll() {
        HibernateUtils.evictLevel2Cache(em);
        HibernateUtils.getSessionFactory(em).getStatistics().clear();
        return "操作成功";
    }
    
    /**
     * 失效实体缓存
     * @author JeeLearner
     * @date 2018年3月26日
     * @param entityNames
     * @param entityIds
     * @return
     */
    @RequestMapping(value = "/evictEntity")
    @ResponseBody
    public String evictEntity(
            @RequestParam(value = "entityNames", required = false) String[] entityNames,
            @RequestParam(value = "entityIds", required = false) Serializable[] entityIds) {

        boolean entityNamesEmpty = ArrayUtils.isEmpty(entityNames);
        boolean entityIdsEmpty = ArrayUtils.isEmpty(entityIds);

        Cache cache = HibernateUtils.getCache(em);

        if(entityNamesEmpty && entityIdsEmpty) {
            cache.evictEntityRegions();
        } else if(entityIdsEmpty) {
            for(String entityName : entityNames) {
                cache.evictEntityRegion(entityName);
            }
        } else {
            for(String entityName : entityNames) {
                for(Serializable entityId : entityIds) {
                    cache.evictEntity(entityName, entityId);
                }
            }
        }

        return "操作成功";
    }
    
    /**
     * 失效实体的集合缓存
     * @author JeeLearner
     * @date 2018年3月26日
     * @param collectionRoleNames
     * @param collectionEntityIds
     * @return
     */
    @RequestMapping(value = "/evictCollection")
    @ResponseBody
    public String evictCollection(
            @RequestParam(value = "collectionRoleNames", required = false) String[] collectionRoleNames,
            @RequestParam(value = "collectionEntityIds", required = false) Serializable[] collectionEntityIds) {


        boolean collectionRoleNamesEmpty = ArrayUtils.isEmpty(collectionRoleNames);
        boolean collectionEntityIdsEmpty = ArrayUtils.isEmpty(collectionEntityIds);

        Cache cache = HibernateUtils.getCache(em);

        if(collectionRoleNamesEmpty && collectionEntityIdsEmpty) {
            cache.evictEntityRegions();
        } else if(collectionEntityIdsEmpty) {
            for(String collectionRoleName : collectionRoleNames) {
                cache.evictCollectionRegion(collectionRoleName);
            }
        } else {
            for(String collectionRoleName : collectionRoleNames) {
                for(Serializable collectionEntityId : collectionEntityIds) {
                    cache.evictCollection(collectionRoleName, collectionEntityIds);
                }
            }
        }
        return "操作成功";
    }

    /**
     * 失效查询缓存
     * @author JeeLearner
     * @date 2018年3月26日
     * @param queries
     * @return
     */
    @RequestMapping(value = "/evictQuery")
    @ResponseBody
    public String evictQuery(
            @RequestParam(value = "queries", required = false) String[] queries) {


        boolean queriesEmpty = ArrayUtils.isEmpty(queries);

        Cache cache = HibernateUtils.getCache(em);

        if(queriesEmpty) {
            cache.evictQueryRegions();
            cache.evictDefaultQueryRegion();
        } else {
            for(String query : queries) {
                cache.evictQueryRegion(query);
            }
        }

        return "操作成功";
    }

	
	/**
	 * 设置内存信息
	 * 
	 * @author JeeLearner
	 * @date 2018年3月26日
	 * @param model
	 */
	private void setMemoryInfo(Model model) {
		// 系统的
		MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		long usedSystemMemory = heapMemoryUsage.getUsed();
		long maxSystemMemory = heapMemoryUsage.getMax();
		model.addAttribute("usedSystemMemory", usedSystemMemory);
		model.addAttribute("maxSystemMemory", maxSystemMemory);

		// 二级缓存的
		Statistics statistics = (Statistics) model.asMap().get("statistics");
		String[] secondLevelCacheRegionNames = statistics.getSecondLevelCacheRegionNames();

		int totalMemorySize = 0;
		int totalMemoryCount = 0;
		int totalDiskCount = 0;

		for (String secondLevelCacheRegionName : secondLevelCacheRegionNames) {
			SecondLevelCacheStatistics secondLevelCacheStatistics = statistics
					.getSecondLevelCacheStatistics(secondLevelCacheRegionName);
			totalMemorySize += secondLevelCacheStatistics.getSizeInMemory();
			totalMemoryCount += secondLevelCacheStatistics.getElementCountInMemory();
			totalDiskCount += secondLevelCacheStatistics.getElementCountOnDisk();
		}

		model.addAttribute("totalMemorySize", totalMemorySize);
		model.addAttribute("totalMemoryCount", totalMemoryCount);
		model.addAttribute("totalDiskCount", totalDiskCount);
	}
}
