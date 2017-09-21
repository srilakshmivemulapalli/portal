package com.nisum.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.rest.api.CategoriesRestService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.util.UserServiceUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public List<UserDTO> getUsers() {
		logger.info("UserServiceImpl :: getUsers :: Get list of users");
		List<User> userList = userDAO.getUsers();
		return UserServiceUtil.convertDaoListTODto(userList);
	}
	@Override
	public String updateUserDetails(User user) {
		logger.info("UserServiceImpl :: updateUserDetails :: Updating user detail");
		return userDAO.updateUser(user);
	}
	@Override
	public int deleteUser(int userId) {
		logger.info("UserServiceImpl :: deleteUser :: Deleteing user");
		return userDAO.deleteUser(userId);
	}
	
	@Override
	public User findUserById(int userId) {
		logger.info("UserServiceImpl :: findUserById :: Finding user by userId");
		User  user=userDAO.findUserById(userId);
		return user;
		
	}

}