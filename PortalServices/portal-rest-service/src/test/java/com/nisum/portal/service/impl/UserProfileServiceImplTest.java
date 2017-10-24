package com.nisum.portal.service.impl;

import static org.mockito.Mockito.when; 

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nisum.portal.data.dao.api.ProfileSettingsDAO;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.util.UserServiceUtil;


@RunWith(PowerMockRunner.class)
@PrepareForTest({UserServiceUtil.class})
public class UserProfileServiceImplTest {
	
	@InjectMocks
	UserProfileServiceImpl userProfileServiceImpl;
	
	@Mock
	ProfileSettingsDAO profileSettingsDAO;
	
	
	@Test
	public void updateUserProfileServiceSuccessTest() 
	{	
		
	UserDTO userDto = new UserDTO();
	userDto.setUserId(1);
	userDto.setEmailId("test@test.com");
	userDto.setLoginDate(new Timestamp(System.currentTimeMillis()));
	userDto.setActiveStatus("YES");
	userDto.setCreateDate(new Timestamp(System.currentTimeMillis()));
	userDto.setUserName("test");
	userDto.setProfileName("abc");
	userDto.setNotifications("course related");
	userDto.setImage("blob");
	userDto.setNotifications(userDto.getNotifications());
	userDto.setProfileName(userDto.getProfileName());
	userDto.setProfileSettings(userDto.getProfileSettings());	
	PowerMockito.mockStatic(UserServiceUtil.class);
	when(userProfileServiceImpl.updateUserProfile(userDto)).thenReturn("success");
	
}
	
	
	@Test
	public void updateUserProfileServiceFailureTest() 
	{	
		
	UserDTO userDto = new UserDTO();
	userDto.setUserId(1);
	userDto.setEmailId("test@test.com");
	userDto.setLoginDate(new Timestamp(System.currentTimeMillis()));
	userDto.setActiveStatus("YES");
	userDto.setCreateDate(new Timestamp(System.currentTimeMillis()));
	userDto.setUserName("test");
	userDto.setProfileName("abc");
	userDto.setNotifications("course related");
	userDto.setImage("blob");
	userDto.setNotifications(userDto.getNotifications());
	userDto.setProfileName(userDto.getProfileName());
	userDto.setProfileSettings(userDto.getProfileSettings());	
	PowerMockito.mockStatic(UserServiceUtil.class);
	when(userProfileServiceImpl.updateUserProfile(userDto)).thenReturn("Fail");
	
}
}
