package com.nisum.portal.util;


import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserRoleDTO;

public class UserRoleServiceUtil {	
	
	public static UserRole convertDtoToDao(UserRoleDTO userRoleDto ) {
		UserRole userRole=new UserRole();
		userRole.setRole(userRoleDto.getRole());
		userRole.setCreatedDate(userRoleDto.getCreatedDate());
		return userRole;
	}

}
