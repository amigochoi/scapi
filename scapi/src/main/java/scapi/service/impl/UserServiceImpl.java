package scapi.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scapi.common.constant.APIConstant;
import scapi.dao.UserDAO;
import scapi.model.display.ResultJson;
import scapi.model.domain.User;
import scapi.model.dto.UserDTO;
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
/*	@org.springframework.cache.annotation.Cacheable(value = "userCache", unless="#result == null")*/
	@Override
	public ResultJson getUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return new ResultJson(APIConstant.Success, new UserDTO(userDAO.getUser(new User(userDTO))));
	}

	//@CachePut(value = "user", key = "#id")
	@Cacheable(cacheName = "userCache", keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"), cacheNull = false)
/*	@org.springframework.cache.annotation.Cacheable(value = "userCache", key = "#id", unless="#result == null")*/
	@Override
	public ResultJson getUserById(Integer id) {
		// TODO Auto-generated method stub
		return new ResultJson(APIConstant.Success,new UserDTO(userDAO.getUserById(id)));
	}
}
