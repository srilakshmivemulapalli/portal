package com.nisum.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.util.UserServiceUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	@Override
	public List<UserDTO> getUsers() {
		List<User> userList = userDAO.getUsers();
		return UserServiceUtil.convertDaoListTODto(userList);
	}
	@Override
	public String updateUserDetails(User user) {

		return userDAO.updateUser(user);
	}
	@Override
	public int deleteUser(int userId) {
		return userDAO.deleteUser(userId);
	}
	
	@Override
	public User findUserById(int userId) {
		User  user=userDAO.findUserById(userId);
		return user;
		
	}

}
