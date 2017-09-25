package com.nisum.portal.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserDAO userDao;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Before
	public void init() {
		
	}
	
	@Test
	public void deleteUser() {
		int userId = 1;
		when(userDao.deleteUser(userId)).thenReturn(userId);
		int actual = userServiceImpl.deleteUser(userId);
		assertEquals(userId, actual);
	}
	
	@Test
	public void findUserById() {
		int userId = 1;
		User user = new User();
		user.setActiveStatus("Yes");
		user.setEmailId("sasas");
		user.setName("asasa");
		user.setUserId(1);
		when(userDao.findUserById(userId)).thenReturn(user);
		String expected = user.getActiveStatus();
		String actual = userServiceImpl.findUserById(userId);
		assertEquals(expected, actual);
	}
	
	@Test
	public void findNullUser() {
		int userId = 0;
		User user = new User();
		when(userDao.findUserById(userId)).thenReturn(user);
		String expected = user.getActiveStatus();
		String actual = userServiceImpl.findUserById(userId);
		assertEquals(expected, actual);
	}
}
