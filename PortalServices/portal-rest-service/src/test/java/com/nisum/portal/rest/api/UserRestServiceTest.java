package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
		
		when(userService.updateUserDetails(user)).thenReturn("Success");
		when(userService.updateUserDetails(null)).thenReturn("Failed");
		assertEquals(new ResponseEntity(null),userRestService.updateUserDetails(user));
	}
	

}
