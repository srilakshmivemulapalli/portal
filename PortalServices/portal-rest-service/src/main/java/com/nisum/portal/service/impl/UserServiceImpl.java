package com.nisum.portal.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
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
