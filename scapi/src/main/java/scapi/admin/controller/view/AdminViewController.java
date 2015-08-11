package scapi.admin.controller.view;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class AdminViewController {
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String viewAdminMenu(){
		return "menu";		
	}
	
	@RequestMapping(value = "/login")
	public String login(){
		Boolean isLogin = false;
		if(SecurityUtils.getSubject().isAuthenticated()) {
		    Session session = SecurityUtils.getSubject().getSession(false);
		    try {
		        session.touch();
		        isLogin = true;
		    } catch (ExpiredSessionException e) {
		        // timeout case.
		    	log.error("login error",e);
		    }
		} else {
		    // not login case.
			log.info("is not login");
		}
		
		return isLogin ? "menu" : "login";
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
