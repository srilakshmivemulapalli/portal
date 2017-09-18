package com.nisum.portal.data.dao.api;

import com.nisum.portal.data.domain.UserRole;

public interface UserRoleDAO {
	UserRole addUser(UserRole userRole);
	boolean deleteUser(int id);
}
