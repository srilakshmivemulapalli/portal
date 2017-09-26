package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.UserRole;

public interface UserRoleDAO {
	UserRole addUserRole(UserRole userRole);
	boolean deleteUserRole(int id);
	List<UserRole> getUserRole();
	public UserRole updateUserRole(UserRole userRole);
	public UserRole findUserById(Integer roleId);
	
}
