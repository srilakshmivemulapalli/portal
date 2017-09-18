package com.nisum.portal.data.dao.api;

import com.nisum.portal.data.domain.User;

public interface UserDAO {

	public int deleteUser(int userId);
	
	public User findUserById(int userId);
}
