package com.nisum.portal.service.api;

import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserRoleDTO;

public interface UserRoleService {
	
	public UserRole addUser(UserRoleDTO userRoleDto);
	public boolean deleteUser(int id);

}
