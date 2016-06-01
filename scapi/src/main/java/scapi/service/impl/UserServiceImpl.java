package scapi.service.impl;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scapi.common.constant.APIConstant;
import scapi.dao.UserDAO;
import scapi.model.display.ResultJson;
import scapi.model.domain.User;
import scapi.model.dto.UserDTO;
import scapi.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
//	@Cacheable(cacheName = "getUsers", keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"), cacheNull = false)
//	@org.springframework.cache.annotation.Cacheable(value = "getUsers", unless="#result == null")
	@Override
	public ResultJson getUsers(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		List<User> users = userDAO.getUsers(modelMapper.map(userDTO, User.class));
	    List<UserDTO> userDTOs = modelMapper.map(users,  new TypeToken<List<UserDTO>>() {}.getType());
	    if(userDTOs == null)
	    	return new ResultJson(APIConstant.UnfoundFail); 
	    else
	    	return new ResultJson(APIConstant.Success, userDTOs);
	}

//	@Cacheable(cacheName = "getUser", keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"), cacheNull = false)
//	@org.springframework.cache.annotation.Cacheable(value = "getUser", key = "#id", unless="#result == null")
	@Override
	public ResultJson getUser(Integer id) {
		User user = userDAO.getUser(id);
		if(user == null)
			return new ResultJson(APIConstant.UnfoundFail);
		else
			return new ResultJson(APIConstant.Success,new UserDTO(userDAO.getUser(id)));
	}

	@Override
	public ResultJson create(UserDTO userDTO) {
		return new ResultJson(APIConstant.Success,new UserDTO(userDAO.create(new User(userDTO))));
	}
}
