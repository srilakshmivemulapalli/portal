package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserRoleDTO;

public class UserServiceUtil {

	public static List<UserDTO> convertDaoListTODto(List<User> users) {
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		for(User user : users)
		{
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setActiveStatus(user.getActiveStatus());
		userDto.setEmailId(user.getEmailId());
		userDto.setLoginDate(user.getLoginDate());
		userDto.setName(user.getName());
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setCreatedDate(user.getRole().getCreatedDate());
		userRoleDTO.setRole(user.getRole().getRole());
		userRoleDTO.setRoleId(user.getRole().getRoleId());
		userDto.setUserRole(userRoleDTO);
		
		userDTO.add(userDto);
		}
		return userDTO;
	}
	public static Object convertDaoObjectTODto(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setName(user.getName());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setLoginDate(user.getLoginDate());
		userDTO.setActiveStatus(user.getActiveStatus());
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setCreatedDate(user.getRole().getCreatedDate());
		userRoleDTO.setRole(user.getRole().getRole());
		userRoleDTO.setRoleId(user.getRole().getRoleId());
		userDTO.setUserRole(userRoleDTO);
		return userDTO;

	}
}
