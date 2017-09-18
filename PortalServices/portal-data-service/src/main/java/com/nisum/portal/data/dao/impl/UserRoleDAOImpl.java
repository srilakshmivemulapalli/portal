package com.nisum.portal.data.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.data.repository.UserRoleRepository;


@Configuration
public class UserRoleDAOImpl implements UserRoleDAO{
	
	@Autowired
	UserRoleRepository userRoleRepository;

	//adding User Role
	@Override
	public UserRole addUser(UserRole userRole) {
		return userRoleRepository.save(userRole);	
	}

	//deleting User Role
	@Override
	public boolean deleteUser(int id) {
			userRoleRepository.delete(id);
		return true;
	}
}
