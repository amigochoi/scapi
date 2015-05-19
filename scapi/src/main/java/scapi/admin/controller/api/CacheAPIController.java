package scapi.admin.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import scapi.admin.model.CacheDisplay;
import scapi.admin.model.CacheElementDisplay;
import scapi.admin.service.CacheMonitoringService;
import scapi.admin.service.RedisCacheMonitoringService;

@Controller
@RequestMapping("/cacheApi")
public class CacheAPIController {
	
	@Autowired
	private CacheMonitoringService cacheMonitoringService;
	
	@Autowired
	private RedisCacheMonitoringService redisCacheMonitoringService;
			
	@RequestMapping(value = "/getAllCacheDisplayList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CacheDisplay> getAllCacheDisplayList() {
		return cacheMonitoringService.getAllCache();
		
	}
	
	@RequestMapping(value = "/getElementList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CacheElementDisplay> getAllCacheDisplayList(String cacheName) {
		return cacheMonitoringService.getAllElements(cacheName);
		
	}
	
	@RequestMapping(value = "/removeCache", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean clearCache(String cacheName) {
		cacheMonitoringService.clearCache(cacheName);
		return true;
	}
	
	@RequestMapping(value = "/removeElement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean removeElement(String cacheName, String cacheKey) {
		cacheMonitoringService.removeElement(cacheName, cacheKey);
		return true;
	}

//	redis related API
	
	@RequestMapping(value = "/getAllRedisCacheDisplayList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CacheDisplay> getAllRedisCacheDisplayList() {
		return redisCacheMonitoringService.getAllCache();
	}
	
	@RequestMapping(value = "/removeRedisCache", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean removeRedisCache(String cacheName) {
		redisCacheMonitoringService.clearCache(cacheName);
		return true;
	}
	
}
