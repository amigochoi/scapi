package scapi.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scapi.common.constant.APIConstant;
import scapi.dao.UserDAO;
import scapi.model.display.ResultJson;
import scapi.model.domain.User;
import scapi.model.dto.UserDTO;
import scapi.model.dto.UsersDTO;
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
		User userParam =  modelMapper.map(userDTO, User.class);
		List<User> users = userDAO.getUsers(userParam);
	    List<UserDTO> userDTOs = modelMapper.map(users,  new TypeToken<List<UserDTO>>() {}.getType());
	    
	    if(userDTOs == null)
	    	return new ResultJson(APIConstant.UnfoundFail); 
	    else
	    	return new ResultJson(APIConstant.Success, new UsersDTO(userDTOs,userDAO.getUsersCount(userParam)));
	}

//	@Cacheable(cacheName = "getUser", keyGenerator = @KeyGenerator(name = "StringCacheKeyGenerator"), cacheNull = false)
//	@org.springframework.cache.annotation.Cacheable(value = "getUser", key = "#id", unless="#result == null")
	@Override
	public ResultJson getUser(Integer id) {
		User user = userDAO.getUser(id);
		if(user == null)
			return new ResultJson(APIConstant.UnfoundFail);
		else{
			return new ResultJson(APIConstant.Success,new UserDTO(user));
		}
	}

	@Override
	public ResultJson create(UserDTO userDTO) {
		return new ResultJson(APIConstant.Success,new UserDTO(userDAO.create(new User(userDTO))));
	}

	@Override
	public ResultJson update(UserDTO userDTO) {
		User user = userDAO.getUser(userDTO.getUserId());
		if(user == null)
			return new ResultJson(APIConstant.UnfoundFail);
		else{
			return new ResultJson(APIConstant.Success,new UserDTO(userDAO.update(user,new User(userDTO)))); 
		}
	}

	@Override
	public ResultJson delete(Integer id) {
		User user = userDAO.getUser(id);
		if(user == null)
			return new ResultJson(APIConstant.UnfoundFail);
		else{
			userDAO.delete(user);
			return new ResultJson(APIConstant.Success,new UserDTO(user)); 
		}
	}
}
