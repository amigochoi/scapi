package scapi.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scapi.dao.UserDAO;
import scapi.model.User;
import scapi.service.UserService;

import com.googlecode.ehcache.annotations.Cacheable;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
//  keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"),,decoratedCacheType=DecoratedCacheType.REFRESHING_SELF_POPULATING_CACHE,refreshInterval=1000*60
	@Cacheable(cacheName = "userCache", cacheNull = false)
	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.getUser(user);
	}

}
