package scapi.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import scapi.model.display.ResultJson;
import scapi.model.dto.UserDTO;
import scapi.service.UserService;

@Controller("userAPIController")
@Slf4j
@RequestMapping("/api/v1/users")
public class UserAPIController {

	@Autowired
	UserService userService;
	// consumes = MediaType.APPLICATION_JSON_VALUE,
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> getUser(@Valid UserDTO userDTO, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultJson(result.getFieldErrors()));
		}else{
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userDTO));
		}
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> getUserById(@PathVariable String userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(Integer.valueOf(userId)));
	}
}
