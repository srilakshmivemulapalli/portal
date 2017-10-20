package com.nisum.portal.util;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserRoleDTO;

public class UserRoleServiceUtil {	
	
	public static UserRole convertDtoToDao(UserRoleDTO userRoleDto ) {
		UserRole userRole=new UserRole();
		userRole.setRole(userRoleDto.getRole());
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		return userRole;
	}

	public static List<UserRoleDTO> convertDaoTODto(List<UserRole>  userRoleList){
		List<UserRoleDTO> userRoleDTOs = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(userRoleList)) {
			for(UserRole userRole:userRoleList){
			 UserRoleDTO userRoleDTO= new UserRoleDTO();		
			 	userRoleDTO.setRoleId(userRole.getRoleId());
			 	userRoleDTO.setRole(userRole.getRole());		
			 	userRoleDTO.setCreatedDate(userRole.getCreatedDate());
			 	userRoleDTOs.add(userRoleDTO);
			}
		}
		return userRoleDTOs;
      }
	
	public static UserRole converDtoTODao(UserRoleDTO userRoleDto) {
		UserRole userRole=new UserRole();
		userRole.setRoleId(userRoleDto.getRoleId());
		userRole.setRole(userRoleDto.getRole());
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		return userRole;
	}
	
	
}
