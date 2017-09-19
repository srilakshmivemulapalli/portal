package com.nisum.portal.util;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UserDTO;

public class UserServiceUtil {

	public static UserDTO convertDaoTODto(User user) {
		UserDTO userDto = new UserDTO();
		//userDto.setUserId(user.getUserId());
		//userDto.setActive(user.isActive());
		//userDto.setEmailId(user.getEmailId());
		userDto.setLoginDate(user.getLoginDate());
		userDto.setName(user.getName());
		//userDto.setRoleId(user.getUserRole());
		return userDto;
	}
}
