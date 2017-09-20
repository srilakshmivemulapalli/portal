package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.User;

/**
 * 
 * @author nisum
 *
 */
public interface UserDAO {

	String updateUser(User user);

	int deleteUser(int userId);

	List<User> getUsers();

	User findUserById(int userId);

}
