package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserRoleServiceException;

public interface UserRoleService {
	
	public UserRole addUserRole(UserRoleDTO userRoleDto) throws UserRoleServiceException;
	public boolean deleteUserRole(Integer id) throws UserRoleServiceException;
	List<UserRoleDTO> getUserRole();
	public UserRole updateUserRole(UserRole userRole);
	public Integer findUserById(Integer roleId);

}
