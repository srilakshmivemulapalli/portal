package com.nisum.portal.data.dao.api;

import com.nisum.portal.data.domain.User;
//import com.nisum.portal.data.domain.UserProfile;

public interface ProfileSettingsDAO {

	
	public String updateProfile(User userProfile );
	public User getUserByEmail(String email);
	
	
}
