package scapi.service;

import scapi.model.display.ResultJson;
import scapi.model.domain.User;

public interface UserService {
	
	public ResultJson getUser(User user);
	
	public ResultJson getUserById(Integer id);
}
