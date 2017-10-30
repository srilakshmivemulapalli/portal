package com.nisum.portal.data.dao.api.impl;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.nisum.portal.data.dao.impl.ProfileSettingsDAOImpl;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.ProfileSetting;
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
	
		
		User userId = new User();
		Categories categoryiId = new Categories();
		userId.setUserId(2);
		categoryiId.setCategoryId(1);
		ProfileSetting profile=new ProfileSetting();
		
		profile.setProfileId(2);
		profile.setUserId(userId);
		profile.setCategoryId(categoryiId);	
		
		
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
		
		User userId = new User();
		Categories categoryiId = new Categories();
		userId.setUserId(2);
		categoryiId.setCategoryId(1);
		ProfileSetting profile=new ProfileSetting();
		
		profile.setProfileId(2);
		profile.setUserId(userId);
		profile.setCategoryId(categoryiId);			
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
