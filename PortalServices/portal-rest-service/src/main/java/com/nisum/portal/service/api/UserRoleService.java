package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserRoleDTO;

public interface UserRoleService {
	
	public UserRole addUser(UserRoleDTO userRoleDto);
	public boolean deleteUser(int id);
	List<UserRoleDTO> getUserRole();
	public UserRole updateUserRole(UserRole userRole);

}
