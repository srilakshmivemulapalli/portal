package com.nisum.portal.data.dao.impl;


import java.util.List; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.data.repository.UserRoleRepository;


@Configuration
public class UserRoleDAOImpl implements UserRoleDAO{
	
	private static Logger logger = LoggerFactory.getLogger(UserRoleDAOImpl.class);
	
	@Autowired
	UserRoleRepository userRoleRepository;

	//adding User Role
	@Override
	public UserRole addUserRole(UserRole userRole) {
		logger.info("UserRoleDAOImpl :: entered into addUserRole("+userRole+")");
		return userRoleRepository.save(userRole);	
	}

	//deleting User Role
	@Override
	public boolean deleteUserRole(int id) {
		logger.info("userRoleDAOImpl: entered into deleteUserRole("+id+")");
			userRoleRepository.delete(id);
		return true;
	}
	
	@Override
	public List<UserRole> getUserRole() {
		logger.info("UserRoleDAOImpl :: getUsers :: Get list of userRoles");
		return userRoleRepository.findAll();
	}

	@Override
	public UserRole updateUserRole(UserRole userRole) 
	{  
		UserRole userRole1=userRoleRepository.findOne(userRole.getRoleId());
		userRole.setCreatedDate(userRole1.getCreatedDate());
		logger.info("UserRoleDAOImpl :: updateUser :: Updating userRole");
		return userRoleRepository.save(userRole);
		}

}
	
