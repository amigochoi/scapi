package scapi.dao;

import scapi.model.domain.User;


public interface UserDAO {
	public User getUser(User user);
	
	public User getUserById(Integer id);
}
