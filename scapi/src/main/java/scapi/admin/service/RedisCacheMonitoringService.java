package scapi.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import scapi.admin.model.CacheDisplay;

@Component
@Slf4j
public class RedisCacheMonitoringService {

	@Autowired
	private RedisCacheManager cacheManager;

	@Resource(name = "redisCacheTimeMap")
	Map<String, String> redisCacheTimeMap;

	/*
	 * Get the cache instance by cacheName from cacheManager.
	 */
	public Cache getCache(String cacheName) {
		Cache cache = null;
		if (StringUtils.isNotBlank(cacheName)) {

			cache = cacheManager.getCache(cacheName);
		}
		return cache;
	}

	public void clearCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			cache.clear();
		}

	}

	public List<CacheDisplay> getAllCache() {
		List<CacheDisplay> cacheDisplayList = new ArrayList<CacheDisplay>();
		for (Map.Entry<String, String> entry : redisCacheTimeMap.entrySet()) {

			Cache cache = cacheManager.getCache(entry.getKey());
			CacheDisplay cacheDisplay = new CacheDisplay();
			if (cache != null) {
				cacheDisplay.setCacheName(cache.getName());
			}
			cacheDisplayList.add(cacheDisplay);
		}
		return cacheDisplayList;
	}

}
