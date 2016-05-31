package scapi.service;

import scapi.model.display.ResultJson;
import scapi.model.dto.UserDTO;

public interface UserService {
	
	public ResultJson getUser(UserDTO userDTO);
	
	public ResultJson getUserById(Integer id);
}
