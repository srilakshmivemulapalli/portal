package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.repository.UserRepository;

@Configuration
public class UserDAOImpl implements UserDAO{

	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		logger.info("UserDAOImpl :: getUsers :: Get list of users");
		return userRepository.findAll();
	}
	
	@Override
	public User updateUser(User user) {
		logger.info("UserDAOImpl :: updateUser :: Updating user");
		if (userRepository.exists(user.getUserId())) {
			return userRepository.save(user);
		} else {
			return null;
		}
	}
	@Override
	public int deleteUser(int userId) {
		logger.info("UserDAOImpl :: deleteUser :: Deleting user");
		return userRepository.deleteUser(userId);
	}
	
	@Override
	public User findUserById(int userId) {
		logger.info("UserDAOImpl :: findUserById :: Finding user by userId");
		return userRepository.findOne(userId);
	}

	@Override
	public long getUserCount() {
		return userRepository.count();
	}

}
