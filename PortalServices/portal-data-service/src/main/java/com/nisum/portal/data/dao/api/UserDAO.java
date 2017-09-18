package com.nisum.portal.data.dao.api;

import com.nisum.portal.data.domain.User;

/**
 * 
 * @author nisum
 *
 */
public interface UserDAO {

	public int deleteUser(int userId);
	
	public User findUserById(int userId);
}
