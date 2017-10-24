package com.nisum.portal.data.dao.api.impl;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.nisum.portal.data.dao.impl.ProfileSettingsDAOImpl;
import com.nisum.portal.data.domain.ProfileSettings;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileDAOImplTest {
	
	
	@InjectMocks
	ProfileSettingsDAOImpl profileSettingsDAOImpl;
	
	
	@Mock
	UserRepository userRepository;
	
	@Test
	public void updateUserProfileSuccessTest() 
	{ 
	
		ProfileSettings profile=new ProfileSettings();
		profile.setProfileId(2);
		profile.setUserId(2);
		profile.setCategoryId(3);		
		User user = new User();
		user.setUserId(1);
		user.setEmailId("test@test.com");
		user.setLoginDate(new Timestamp(System.currentTimeMillis()));
		user.setActiveStatus("YES");
		user.setUserName("test");
		user.setNotifications("course related");
		user.setProfileName("mahadev");
		when(userRepository.exists(user.getUserId())).thenReturn(true);
	}
	@Test
	public void updateUserProfileFailureTest() 
	{ 	
		
		ProfileSettings profile=new ProfileSettings();
		profile.setProfileId(2);
		profile.setUserId(2);
		profile.setCategoryId(3);		
		User user = new User();
		user.setUserId(1);
		user.setEmailId("test@test.com");
		user.setLoginDate(new Timestamp(System.currentTimeMillis()));
		user.setActiveStatus("YES");
		user.setUserName("test");
		user.setNotifications("course related");
		user.setProfileName("mahadev");
		when(userRepository.exists(user.getUserId())).thenReturn(false);
		
	}
}
