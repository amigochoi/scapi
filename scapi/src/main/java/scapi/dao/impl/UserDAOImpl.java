package scapi.dao.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import scapi.dao.UserDAO;
import scapi.model.User;

@Repository("userDAO")
@Slf4j
public class UserDAOImpl implements UserDAO{
	
	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return user;
	}


}
