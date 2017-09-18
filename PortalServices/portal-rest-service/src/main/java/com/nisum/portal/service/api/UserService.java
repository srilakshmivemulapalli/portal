package com.nisum.portal.service.api;

import com.nisum.portal.data.domain.User;

public interface UserService {

	public int deleteUser(int userId);
	
	public User findUserById(int UserId);
}
