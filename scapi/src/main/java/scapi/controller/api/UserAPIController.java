package scapi.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import scapi.model.display.ResultJson;
import scapi.model.dto.UserDTO;
import scapi.model.request.ListParam;
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
		@ApiImplicitParam(name = "userName", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userEmail", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "userPhone", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "sorts", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "page", required = false, dataType = "number", paramType = "query"),
		@ApiImplicitParam(name = "count", required = false, dataType = "number", paramType = "query")})
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> getUsers(UserDTO userDTO, ListParam listParam) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(userDTO,listParam));
	}
	
	/*
	 * GET
	 * users/{id}
	 * get a single user record
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> getUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(userService.getUser(userId));
		
	}
	
	/*
	 * PUT
	 * users/{id}
	 * update a user
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> updateUser(@PathVariable Integer userId, @RequestBody @Valid UserDTO userDTO, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultJson(result.getFieldErrors()));
		}else{
			userDTO.setUserId(userId);
			return ResponseEntity.ok(userService.update(userDTO));
		}
	}
	
	/*
	 * DELETE
	 * users/{id}
	 * delete a user
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> deleteUser(@PathVariable Integer userId) {		
		return ResponseEntity.ok(userService.delete(userId));
	}
	
	
	/*
	 * POST
	 * users
	 * create a user
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResultJson> createUser(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultJson(result.getFieldErrors()));
		}else{
			return ResponseEntity.ok(userService.create(userDTO));
		}
	}
}
