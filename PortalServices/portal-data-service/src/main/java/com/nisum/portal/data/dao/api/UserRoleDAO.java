package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.UserRole;

public interface UserRoleDAO {
	UserRole addUser(UserRole userRole);
	boolean deleteUser(int id);
	List<UserRole> getUserRole();
	public UserRole updateUserRole(UserRole userRole);
	
	
}
