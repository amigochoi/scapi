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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	
	
	/*
	 * GET
	 * users
	 * get list of users
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", required = false, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "userName", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userEmail", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userPhone", required = false, dataType = "string", paramType = "query") })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> getUsers(UserDTO userDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(userDTO));
	}
	
	/*
	 * GET
	 * users/{id}
	 * get a single user record
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", required = false, dataType = "int",defaultValue="0", paramType = "path")
	 })	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> getUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.getUser(userId));
		
	}
	
	/*
	 * POST
	 * users
	 * create a user
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userName", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userEmail", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userPhone", required = false, dataType = "string", paramType = "query") })
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> createUser(@Valid UserDTO userDTO, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultJson(result.getFieldErrors()));
		}else{
			return ResponseEntity.ok(userService.create(userDTO));
		}
	}
}
