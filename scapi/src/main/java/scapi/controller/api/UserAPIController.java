package scapi.controller.api;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import scapi.model.display.ResultJson;
import scapi.service.UserService;

@Controller("userAPIController")
@Slf4j
@RequestMapping("/user")
public class UserAPIController {

	@Autowired
	UserService userService;
	// consumes = MediaType.APPLICATION_JSON_VALUE,
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResultJson getUser() {
		return new ResultJson(userService.getUser());
	}
}
