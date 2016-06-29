package scapi.service;

import scapi.model.display.ResultJson;
import scapi.model.dto.UserDTO;
import scapi.model.request.ListParam;

public interface UserService {
	
	public ResultJson getUsers(UserDTO userDTO, ListParam listParam);
	
	public ResultJson getUser(Integer id);
	
	public ResultJson create(UserDTO userDTO);
	
	public ResultJson update(UserDTO userDTO);
	
	public ResultJson delete(Integer id);
}
