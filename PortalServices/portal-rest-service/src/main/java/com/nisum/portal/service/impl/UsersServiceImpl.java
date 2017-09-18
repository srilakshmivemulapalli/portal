package com.nisum.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.UsersDAO;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UsersService;

import com.nisum.portal.service.dto.UsersDTO;

import com.nisum.portal.util.UsersServiceUtil;
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDAO usersDAO;

	@Override
	public List<UsersDTO> getUsers() {
		List<User>  usersList=usersDAO.getUsers();
		return UsersServiceUtil.convertDaoTODto(usersList);
	}

}
