package scapi.dao;

import java.util.List;

import scapi.model.domain.User;

public interface UserDAO{
	
	public List<User> getUsers(User user);
	
	public int getUsersCount(User user);
	
	public User getUser(Integer id);
	
	public User create(User user);
	
	public User update(User oldUser, User user);
	
	public void delete(User user);
}
