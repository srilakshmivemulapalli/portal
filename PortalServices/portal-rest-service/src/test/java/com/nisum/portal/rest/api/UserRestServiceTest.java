package com.nisum.portal.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.exception.UserServiceException;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRestServiceTest {
	
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserRestService userRestService;
	
	@Before
	public void init() {
		
	}
	
	
	@Test
	public void TestUserServiceRest() throws UserServiceException {
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.add(1);
		User user = new User();
		user.setName("Radhika");
		user.setEmailId("radhika@nisum.com");
		String expectedValue = "1 Users Deleted,0 Failed to delete";
		for (int userId: userIds) {
			Mockito.when(userService.findUserById(userId)).thenReturn(user);
			Mockito.when(userService.deleteUser(userId)).thenReturn(1);
			String actualMessage = userRestService.deleteUser(userIds);
			Assert.assertEquals(expectedValue,actualMessage);
		}
	}
	
	
	
}
