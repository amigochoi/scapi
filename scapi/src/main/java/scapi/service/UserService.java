package scapi.service;

import scapi.model.User;

public interface UserService {
	
	public User getUser(User user);
	
	public User getUserById(Integer id);
}
