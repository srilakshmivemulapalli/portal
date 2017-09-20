package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;
import com.nisum.portal.data.domain.User;
<<<<<<< HEAD
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
=======
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserRoleDTO;
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
			UserRoleDTO userRoleDTO = new UserRoleDTO();
			userRoleDTO.setCreatedDate(users.getRole().getCreatedDate());
			userRoleDTO.setRole(users.getRole().getRole());
			userRoleDTO.setRoleId(users.getRole().getRoleId());
			userDTO.setUserRole(userRoleDTO);
>>>>>>> 35cdf07be8678678b927e67985b04b9b8ddee2d1
			
			userDTOs.add(userDTO);
		}
		return userDTOs;
		
	}

}
