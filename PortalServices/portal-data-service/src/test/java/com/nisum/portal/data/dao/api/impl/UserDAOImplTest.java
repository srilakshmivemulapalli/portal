package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.UserDAOImpl;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.data.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOImplTest {
	
	@InjectMocks
	UserDAOImpl userDAOImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Test
	public void updateUser() 
	{
		String s = "Success";
		String f = "Failed";
		UserRole role = new UserRole();
		User user = new User();
		user.setUserId(1);
		user.setEmailId("test@test.com");
		user.setLoginDate("1507190450000");
		user.setActiveStatus("YES");
		user.setName("test");
		role.setRoleId(1);
		role.setRole("Admin");
		role.setCreatedDate("1507190450000");
		user.setRole(role);
		when(userRepository.exists(user.getUserId())).thenReturn(true);
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(null);
		assertEquals(s,userDAOImpl.updateUser(user));
		
	}

	

}
