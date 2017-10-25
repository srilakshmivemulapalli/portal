package com.nisum.portal.data.dao.api;


import com.nisum.portal.data.domain.User;


public interface ProfileSettingsDAO {

	public String updateProfile(User userProfile);

	public User getUserByEmail(String email);
	
	public User getUserProfile(User userid);
	
	//public User deleteUserCategories(int userid);

	public int deleteCategory(String emailId);



}
