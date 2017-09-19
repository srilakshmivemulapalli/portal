package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UsersDTO;

public class UsersServiceUtil {
	public static List<UserDTO> convertDaoTODto(List<User>  usersList){
		List<UserDTO> userDTOs = new ArrayList<>();
		for(User users:usersList){
			UserDTO userDTO= new UserDTO();
			userDTO.setUserId(users.getUserId());
			userDTO.setEmailId(users.getEmailId());
			userDTO.setName(users.getName());
			userDTO.setLoginDate(users.getLoginDate());
			userDTO.setRoleId(users.getRole());
			
			userDTOs.add(userDTO);
		}
		return userDTOs;
		
	}

}
