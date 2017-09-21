package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserService;

public class UserRestServiceTest {
	
	@InjectMocks
	UserRestService userRestService;
	
	@Mock
	UserService userService;
	
	@Test
	public void updateUser()
	{
		Date date = new Date();
		UserRole role = new UserRole();
		User user = new User();
		user.setUserId(1);
		user.setEmailId("test@test.com");
		user.setLoginDate(new Timestamp(date.getTime()));
		user.setActiveStatus("YES");
		user.setName("test");
		role.setRoleId(1);
		role.setRole("Admin");
		role.setCreatedDate(new Timestamp(date.getTime()));
		user.setRole(role);
		
	//	when(userService.updateUserDetails(user)).thenReturn("Success");
		when(userService.updateUserDetails(null)).thenReturn("Failed");
	//	assertEquals(new ResponseEntity(null),userRestService.updateUser(user));
	}
	

}
