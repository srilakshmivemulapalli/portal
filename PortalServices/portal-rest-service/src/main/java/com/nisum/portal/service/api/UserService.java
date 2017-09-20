package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UserDTO;

public interface UserService {
	public int deleteUser(int userId);
	String updateUserDetails(User user);
	public User findUserById(int userId);
	List<UserDTO> getUsers();
}
