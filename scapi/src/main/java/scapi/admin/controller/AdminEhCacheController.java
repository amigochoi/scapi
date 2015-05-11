package scapi.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scapi.admin.model.CacheDisplay;
import scapi.admin.model.CacheElementDisplay;
import scapi.admin.service.CacheMonitoringService;

@Controller
public class AdminEhCacheController {
	
	@Autowired
	private CacheMonitoringService cacheMonitoringService;
		

	@RequestMapping(value = "/cacheMonitoring")
	public String getAllCache(Model model) throws Exception {
		List<CacheDisplay> cacheDisplayList = cacheMonitoringService.getAllCache();
		model.addAttribute("cacheDisplayList", cacheDisplayList);
		return "cacheMonitoring";
	}

	@RequestMapping("/flashCache")
	public @ResponseBody Boolean clearCache(String cacheName) {
		cacheMonitoringService.clearCache(cacheName);
		return true;
	}

	@RequestMapping("/elementList")
	public String getElementList(String cacheName, Model model) {

		List<CacheElementDisplay> elementList = cacheMonitoringService.getAllElements(cacheName);

		model.addAttribute("elementList", elementList);
		model.addAttribute("cacheName", cacheName);
		return "cacheElementList";
	}

	@RequestMapping("/removeElement")
	public @ResponseBody Boolean removeElement(@RequestParam("cacheName") String cacheName, @RequestParam("cacheKey") String cacheKey) {
		cacheMonitoringService.removeElement(cacheName, cacheKey);
		return true;
	}
	
	
}
