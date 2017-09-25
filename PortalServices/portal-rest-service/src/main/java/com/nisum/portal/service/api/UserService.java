package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UserDTO;

/**
 * 
 * @author nisum
 *
 */
public interface UserService {
	
	/**
	 * Deletes given user
	 * @param userId
	 * @return No.of deleted users count
	 */
	public int deleteUser(int userId);
	
	/**
	 * Updates given user
	 * @param user
	 * @return 
	 */
	User updateUserDetails(UserDTO user);
	
	/**
	 * Find user given by userId
	 * @param userId
	 * @return user activeStatus
	 */
	public String findUserById(int userId);
	
	/**
	 * Returns list of users
	 * @return list of userDto objects
	 */
	List<UserDTO> getUsers();
	
	/**
	 * Returns users count
	 * @return No.of users count
	 */
	long getUserCount();
}
