package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UsersDTO;

public class UsersServiceUtil {
	public static List<UsersDTO> convertDaoTODto(List<User>  usersList){
		List<UsersDTO> userDTOs = new ArrayList<>();
		for(User users:usersList){
			UsersDTO userDTO= new UsersDTO();
			userDTO.setUserid(users.getUserid());
			userDTO.setEmailid(users.getEmailid());
			userDTO.setName(users.getName());
			userDTO.setLoginDate(users.getLoginDate());
			userDTO.setRoleId(users.getRole());
			
			userDTOs.add(userDTO);
		}
		return userDTOs;
		
	}

}
