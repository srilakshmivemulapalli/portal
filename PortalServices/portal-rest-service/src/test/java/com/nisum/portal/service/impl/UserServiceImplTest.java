package com.nisum.portal.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserDAO userDAO;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void DeleteTestUserServiceImpl() {
		int actualUserId = 1;
		Mockito.when(userDAO.deleteUser(1)).thenReturn(actualUserId);
		int expectedUserId = userServiceImpl.deleteUser(1);
		assertEquals(expectedUserId, actualUserId);
		
	}
	
	@Test
	public void FindTestUserServiceImpl() {
		int userValue = 1;
		User user = new User();
		UserRole role = new UserRole();
		role.setRoleId(1);
		user.setEmailId("sddas");
		//user.setLoginDate(22323232);
		user.setName("nsaas");
		//user.setRole(role.getRoleId());
		Mockito.when(userDAO.findUserById(userValue)).thenReturn(user);
		User actual = userDAO.findUserById(1);
		assertEquals(user,actual);
	}
	
}
