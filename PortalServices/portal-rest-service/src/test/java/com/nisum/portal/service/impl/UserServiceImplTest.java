package com.nisum.portal.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.util.UserRoleServiceUtil;
import com.nisum.portal.util.UserServiceUtil;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class) 
@PrepareForTest(UserServiceUtil.class)
public class UserServiceImplTest {

	@Mock
	UserDAO userDao;
	
	@InjectMocks
	UserServiceImpl userServiceImpl=new  UserServiceImpl();
	
	
	
	List<UserDTO> expected;
	@Before
	public void init() {
		expected = new ArrayList<>();
		UserDTO userDTO = new UserDTO();
		userDTO.setActiveStatus("yes");
		userDTO.setName("Radhika");
		
		expected.add(userDTO);
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
	@Test
	public void test_GetUser() {
		List<User> userList = new ArrayList<>();
		User user = new User();
		user.setActiveStatus("Yes");
		user.setName("mg");
		user.setLoginDate(new Timestamp(System.currentTimeMillis()));
		userList.add(user);
		when(userDao.getUsers()).thenReturn(userList);

		List<UserDTO> userDAOlist = new ArrayList<>();
		UserDTO UserDTO = new UserDTO();
		UserDTO.setActiveStatus("Yes");
		UserDTO.setName("mg");
		
		 userDAOlist .add(UserDTO);


		
		PowerMockito.mockStatic(UserServiceUtil.class);
		PowerMockito.when(UserServiceUtil.convertDaoListTODto(userList)).thenReturn(userDAOlist);
		List<UserDTO> actual = userServiceImpl.getUsers();
		
		assertEquals(actual.size(), expected.size());
	}

	
}
