package scapi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import scapi.dao.UserDAO;
import scapi.model.domain.User;

@Repository("userDAO")
@Slf4j
public class UserDAOImpl implements UserDAO{
	
	@Override
	public List<User> getUsers(User user) {
		List<User> users = new ArrayList<User>();
		users.add(new User(1,"sc1","sc1@gmail.com","66666661"));
		users.add(new User(2,"sc2","sc2@gmail.com","66666662"));
		users.add(new User(3,"sc3","sc3@gmail.com","66666663"));
		users.add(new User(4,"sc4","sc4@gmail.com","66666664"));
		return users;
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(id);
		return user;
	}

	@Override
	public User create(User user) {
		return user;
	}


}
