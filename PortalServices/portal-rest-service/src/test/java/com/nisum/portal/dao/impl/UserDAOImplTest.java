package com.nisum.portal.dao.impl;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.UserDAOImpl;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.data.repository.UserRepository;
@RunWith(MockitoJUnitRunner.class)
public class UserDAOImplTest {
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	User user;
	
	@Mock
	UserRole userRole;
	
	@InjectMocks
	UserDAOImpl userDAOImpl;
	
	@Before
	public void init() {
		
	}
	@Test
	public void DeleteTestUserDAOImpl() {
		
		int actualValue = 1;
		Mockito.when(userRepository.deleteUser(1)).thenReturn(actualValue);
		int expected = userDAOImpl.deleteUser(actualValue);
		assertEquals(expected, actualValue);
	}
	
	@Test
	public void FindTestUserDAOImpl() {
		int userValue = 1;
		User user = new User();
		UserRole role = new UserRole();
		role.setRoleId(1);
		user.setEmailId("sddas");
		//user.setLoginDate(22323232);
		user.setName("nsaas");
		//user.setRole(role.getRoleId());
		Mockito.when(userRepository.findOne(userValue)).thenReturn(user);
		User actual = userDAOImpl.findUserById(1);
		assertEquals(user,actual);
	}

}
