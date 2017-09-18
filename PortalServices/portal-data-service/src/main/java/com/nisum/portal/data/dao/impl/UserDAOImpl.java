package com.nisum.portal.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.repository.UserRepository;

@Configuration
public class UserDAOImpl implements UserDAO{

	@Autowired
	UserRepository userRepository;
	@Override
	public int deleteUser(int userId) {
	return userRepository.deleteUser(userId);
	}
	
	@Override
	public User findUserById(int userId) {
		return userRepository.findOne(userId);
	}

}
