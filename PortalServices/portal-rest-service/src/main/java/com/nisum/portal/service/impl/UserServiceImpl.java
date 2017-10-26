package com.nisum.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.ProfileSettingsDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.util.UserServiceUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProfileSettingsDAO profileSettingsDAO;

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	@Cacheable(value = "Users")
	public Map<String, UserDTO> getUsers() {
		logger.info("UserServiceImpl :: getUsers :: Get list of users");
		List<User> userList = userDAO.getUsers();
		List<UserDTO> list = UserServiceUtil.convertDaoListToDto(userList);
		Map<String, UserDTO> userMap = new HashMap<String, UserDTO>();
		for (UserDTO userDTO : list) {
			String email = userDTO.getEmailId();
			userMap.put(email, userDTO);
		}
		return userMap;
	}
	@Override
	@CacheEvict(value = "Users", key = "#userDto" , allEntries=true, condition="#result!=null")
	public UserDTO updateUserDetails(UserDTO userDto) {
		logger.info("UserServiceImpl :: updateUserDetails :: Updating user detail");
		User user = UserServiceUtil.convertDtoObjectTODao(userDto);
		String userStatus = findUserById(user.getUserId());
		if (userStatus != null) {
			profileSettingsDAO.deleteCategory(user.getEmailId());

			User userDao = userDAO.updateUser(user);
			return UserServiceUtil.convertDaoObjectToDto(userDao);
		} else {
			return null;
		}
		}
	@Override
	@CacheEvict(value = "Users", key = "#userId")
	public int deleteUser(int userId) {
		logger.info("UserServiceImpl :: deleteUser :: Deleteing user");
		return userDAO.deleteUser(userId);
	}

	@Override
	@Cacheable(value = "Users", key = "#userId",unless="#result==null")
	public String findUserById(int userId) {
		logger.info("UserServiceImpl :: findUserById :: Finding user by userId");
		User  user=userDAO.findUserById(userId);
		String activeStatus = null;
		if (user != null) {
			activeStatus = user.getActiveStatus();
		}
		return activeStatus;
	}
	@Override
	public long getUserCount() {
		return userDAO.getUserCount();
	}

	@Override
	public void saveUser(UserDTO userDto) {
		User user = UserServiceUtil.convertDtoObjectTODao(userDto);
		userDAO.saveUser(user);
	}


	@Override
	public void create(UserDTO userDto) {
		User user = UserServiceUtil.convertDtoObjectTODao(userDto);
		userDAO.saveUser(user);

	}
	@Override
	@Cacheable(value = "Users", key = "#emailId", unless="#result==null")
	public UserDTO findByEmailId(String emailId) {
		User user=userDAO.findByEmailId(emailId);
		if(user!=null){
			return UserServiceUtil.convertDaoObjectToDto(user);
		}
		return null;
	}

}
