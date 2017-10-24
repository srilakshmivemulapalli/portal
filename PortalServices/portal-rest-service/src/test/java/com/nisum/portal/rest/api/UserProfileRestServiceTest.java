package com.nisum.portal.rest.api;



import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserProfileService;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.UserProfileDTO;
import com.nisum.portal.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileRestServiceTest {
	
	 @InjectMocks
	 UserProfileRestServices userProfileRestServices;
	 
	 @Mock
	 UserProfileService userProfileService;
	 
	 
	    @Test
		public void updateUserProfileServiceTest() 
		{
		 
		    ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
			statusDtoExpected.setStatus(true);
			statusDtoExpected.setMessage(Constants.USER_ROLE_UPDATED);
		   
			UserProfileDTO UserProfileDTO=new UserProfileDTO();
			
			User userDto = new User();
			userDto.setUserId(1);
			userDto.setEmailId("test@test.com");
			userDto.setLoginDate(new Timestamp(System.currentTimeMillis()));
			userDto.setActiveStatus("YES");
			userDto.setCreateDate(new Timestamp(System.currentTimeMillis()));
			userDto.setUserName("test");
			userDto.setProfileName("abc");
			userDto.setNotifications("course related");
			userDto.setImage("blob");	
			when(userProfileService.getUserByMailID(UserProfileDTO.getEmailId())).thenReturn(userDto);
			
			
		 
		}
		 
	   @Test
		public void updateUserProfileServiceFailureTest() 
		{
		 
		    ServiceStatusDto statusDtoExpected=new ServiceStatusDto();
			statusDtoExpected.setStatus(true);
			statusDtoExpected.setMessage(Constants.USER_ROLE_UPDATED);
		   
			UserProfileDTO UserProfileDTO=new UserProfileDTO();
			
			User userDto = new User();
			userDto.setUserId(1);
			userDto.setEmailId("test@test.com");
			userDto.setLoginDate(new Timestamp(System.currentTimeMillis()));
			userDto.setActiveStatus("YES");
			userDto.setCreateDate(new Timestamp(System.currentTimeMillis()));
			userDto.setUserName("test");
			userDto.setProfileName("abc");
			userDto.setNotifications("course related");
			userDto.setImage("blob");	
			when(userProfileService.getUserByMailID(UserProfileDTO.getEmailId())).thenReturn(userDto);
			
			
		 
		}
		 
}

