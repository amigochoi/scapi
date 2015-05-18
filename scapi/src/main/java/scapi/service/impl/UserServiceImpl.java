package scapi.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scapi.dao.UserDAO;
import scapi.model.User;
import scapi.service.UserService;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
//  keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"),,decoratedCacheType=DecoratedCacheType.REFRESHING_SELF_POPULATING_CACHE,refreshInterval=1000*60
	@Cacheable(cacheName = "userCache", keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"), cacheNull = false)
	@org.springframework.cache.annotation.Cacheable(value = "userCache", unless="#result == null")
	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.getUser(user);
	}

	//@CachePut(value = "user", key = "#id")
	@Cacheable(cacheName = "userCache", keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"), cacheNull = false)
	@org.springframework.cache.annotation.Cacheable(value = "userCache", key = "#id", unless="#result == null")
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDAO.getUserById(id);
	}
}
