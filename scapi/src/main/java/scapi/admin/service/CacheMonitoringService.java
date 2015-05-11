package scapi.admin.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.util.TimeUtil;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import scapi.admin.model.CacheDisplay;
import scapi.admin.model.CacheElementDisplay;

@Component
@Slf4j
public class CacheMonitoringService {
	
	@Autowired
	private CacheManager cacheManager;


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

	/*
	 * Get All Cache instances from cacheManager for cache monitoring list
	 */
	public List<CacheDisplay> getAllCache() throws IllegalStateException, CacheException {

		String[] cacheNameArr = cacheManager.getCacheNames();
		List<String> cacheNameList = new ArrayList<String>();

		if (ArrayUtils.isNotEmpty(cacheNameArr)) {
			cacheNameList = new ArrayList<String>(Arrays.asList(cacheNameArr));
		}

		List<CacheDisplay> cacheDisplayList = new ArrayList<CacheDisplay>();
		for (String cacheName : cacheNameList) {
			Cache cache = (Cache) cacheManager.getCache(cacheName);

			CacheDisplay cacheDisplay = new CacheDisplay();
			if (cache != null) {
				cacheDisplay.setCacheName(cache.getName());
				cacheDisplay.setNumberOfElements(cache.getSize());
				cacheDisplay.setLiveSeconds(cache.getCacheConfiguration().getTimeToLiveSeconds());
				cacheDisplay.setStatus(cache.getStatus().toString());

				/**
				 * Warning:This method can be very expensive to run.
				 */
				cacheDisplay.setInMemorySize(cache.calculateInMemorySize()/1024);

			}
			cacheDisplayList.add(cacheDisplay);
		}

		return cacheDisplayList;
	}

	/*
	 * Flush cache instance by the cacheName.
	 */
	public void clearCache(String cacheName) {

		if (StringUtils.isNotBlank(cacheName)) {
			Cache cache = getCache(cacheName);
			if (cache != null) {
				cache.flush();
			}
		}
	}

	/*
	 * Flush all cache instance from cacheManager.
	 */
	public void clearAllCache() {

		if (cacheManager != null) {
			cacheManager.clearAll();
		}
	}

	/*
	 *Get all elements by cacheName. 
	 */
	@SuppressWarnings("unchecked")
	public List<CacheElementDisplay> getAllElements(String cacheName) {
		List<String> cacheKeys = new ArrayList<String>();
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cacheKeys = cache.getKeys();
		}

		List<CacheElementDisplay> elementList = new ArrayList<CacheElementDisplay>();
		for (String cacheKey : cacheKeys) {
		
			Element element = cache.getQuiet(cacheKey);
			CacheElementDisplay ceDisplay = new CacheElementDisplay();

			if (element != null) {
				ceDisplay.setCacheKey(cacheKey);
				ceDisplay.setHitCount(element.getHitCount());
				ceDisplay.setTimeToLive(element.getTimeToLive());
				ceDisplay.setSizeOfMomery(element.getSerializedSize()/1024);

				try {
					Long creationTime = element.getCreationTime();
					Long expirationTime = element.getExpirationTime();
					Long lastAccessTime = element.getLastAccessTime();
					String dateTimeFormat = "MM-dd HH:mm";
					SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
					
					ceDisplay.setCreationTime(sdf.format(new Timestamp(creationTime)));
					ceDisplay.setExpirationTime(sdf.format(new Timestamp(expirationTime)));
					ceDisplay.setLastAccessTime(sdf.format(new Timestamp(lastAccessTime)));

					Integer remainTime = TimeUtil.toSecs(-(System.currentTimeMillis()-expirationTime));
					ceDisplay.setRemainTime(remainTime);
				} catch (Exception ex) {
					log.error("getAllElements error ", ex);
				}

				elementList.add(ceDisplay);

			}
		}
		return elementList;
	}

	/*
	 * Remove element from cache by cacheKey.
	 */
	public void removeElement(String cacheName, String cacheKey) {

		Cache cache = getCache(cacheName);
		if (cache != null) {
			cache.remove(cacheKey);
		}
	}
	
	/*
	 * Check Cache Name is exists
	 */
	public boolean cacheExists(String cacheName){
		return cacheManager.cacheExists(cacheName);
	}
}
