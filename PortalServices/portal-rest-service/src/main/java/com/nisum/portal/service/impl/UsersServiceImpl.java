package com.nisum.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.dao.api.UsersDAO;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UsersService;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UsersDTO;

import com.nisum.portal.util.UsersServiceUtil;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<UserDTO> getUsers() {
		List<User> usersList = userDAO.getUsers();
		return UsersServiceUtil.convertDaoTODto(usersList);
	}

}