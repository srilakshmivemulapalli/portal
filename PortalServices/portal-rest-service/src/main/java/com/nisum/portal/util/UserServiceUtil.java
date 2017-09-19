package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UserDTO;

public class UserServiceUtil {

	public static List<UserDTO> convertDaoListTODto(List<User> users) {
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		for(User user : users)
		{
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setActive(user.isActive());
		userDto.setEmailId(user.getEmailId());
		userDto.setLoginDate(user.getLoginDate());
		userDto.setName(user.getName());
		userDto.setRoleId(user.getUserRole());
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
		userDTO.setActive(user.isActive());
		userDTO.setRoleId(user.getUserRole());
		return userDTO;

	}
}
