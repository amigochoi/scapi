package scapi.admin.controller.view;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminViewController {
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String viewAdminMenu(){
		return "menu";		
	}
	
	@RequestMapping(value = "/cacheList", method = RequestMethod.GET)
	public String viewCacheList(){
		return "cacheList";		
	}
	
	@RequestMapping(value = "/redisCacheList", method = RequestMethod.GET)
	public String viewRedisCacheList(){
		return "redisCacheList";		
	}	
}
