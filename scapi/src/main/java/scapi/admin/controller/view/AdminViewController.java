package scapi.admin.controller.view;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminViewController {
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String viewAdminMenu(){
		return "menu";		
	}
	
	@RequestMapping(value = "/cacheList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String viewCacheList(){
		return "cacheList";		
	}
	
}
