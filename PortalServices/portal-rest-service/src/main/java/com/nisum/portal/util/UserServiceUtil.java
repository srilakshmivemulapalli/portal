package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserRoleDTO;

public class UserServiceUtil {

	/**
	 * Converts List of UserDAO objects to UserDTO objects
	 * @param users
	 * @return List of UserDTO objects
	 */
	public static List<UserDTO> convertDaoListToDto(List<User> users) {
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		
		if (CollectionUtils.isNotEmpty(users)) {
			for(User user : users){
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
				userDto.setRole(userRoleDTO);
				userDTO.add(userDto);
			}
		}
		return userDTO;
	}

	/**
	 * Converts UserDAO object to UserDTO object
	 * @param user
	 * @return UserDTO object
	 */
	public static UserDTO convertDaoObjectToDto(User user) 
	{
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
		userDTO.setRole(userRoleDTO);
		return userDTO;

	}
	
	/**
	 * Converts list of user dto objects to dao list
	 * @param userDtoList
	 * @return List of user objects
	 */
	public static List<User> convertDtoListTODao(List<UserDTO> userDtoList) {
		List<User> users = new ArrayList<User>();
		if (CollectionUtils.isNotEmpty(userDtoList)) {
			for(UserDTO userlist : userDtoList)
			{
				User user = new User();
				user.setUserId(userlist.getUserId());
				user.setActiveStatus(userlist.getActiveStatus());
				user.setEmailId(userlist.getEmailId());
				user.setLoginDate(userlist.getLoginDate());
				user.setName(userlist.getName());
				UserRole userRole = new UserRole();
				userRole.setCreatedDate(userlist.getRole().getCreatedDate());
				userRole.setRole(userlist.getRole().getRole());
				userRole.setRoleId(userlist.getRole().getRoleId());
				user.setRole(userRole);
				users.add(user);
			}
		}
		return users;
	}
	
	/**
	 * Converts dto object to dao object
	 * @param userdto 
	 * @return user object
	 */
	public static User convertDtoObjectTODao(UserDTO userdto) 
	{
		User user = new User();
		user.setUserId(userdto.getUserId());
		user.setName(userdto.getName());
		user.setEmailId(userdto.getEmailId());
		user.setLoginDate(userdto.getLoginDate());
		user.setActiveStatus(userdto.getActiveStatus());
		UserRole userRole = new UserRole();
		userRole.setCreatedDate(userdto.getRole().getCreatedDate());
		userRole.setRole(userdto.getRole().getRole());
		userRole.setRoleId(userdto.getRole().getRoleId());
		user.setRole(userRole);
		return user;
	}
}