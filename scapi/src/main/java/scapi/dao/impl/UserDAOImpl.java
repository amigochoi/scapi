package scapi.dao.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import scapi.dao.UserDAO;
import scapi.model.domain.User;

@Repository("userDAO")
@Slf4j
public class UserDAOImpl implements UserDAO{
	
	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(id);
		return user;
	}


}
