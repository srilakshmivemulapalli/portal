package com.nisum.portal.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.util.UserRoleServiceUtil;

@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	UserRoleDAO userRoleDao;
	
	//Adds User Role into database 
	@Override
	public UserRole addUser(UserRoleDTO userRoleDto) {

	
			UserRole userRole=UserRoleServiceUtil.convertDtoToDao(userRoleDto);
	
		return userRoleDao.addUser(userRole);    
	
	}
	
	//Deletes User Role from Database
	@Override
	public boolean deleteUser(int id) {
		
		return userRoleDao.deleteUser(id);	
		
	}
	
     @Override
	public List<UserRoleDTO> getUserRole() {		
		List<UserRole>  userRoleList=userRoleDao.getUserRole();
		return UserRoleServiceUtil.convertDaoTODto(userRoleList);
	}

	@Override
	public UserRole updateUserRole(UserRole userRole) {
		
		return userRoleDao.updateUserRole(userRole);
	}
}
