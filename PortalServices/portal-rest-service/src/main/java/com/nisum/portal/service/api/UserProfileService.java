package com.nisum.portal.service.api;


import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserProfileDTO;

public interface UserProfileService {

	public String updateUserProfile(UserDTO profile);

	// UserDTO getUserProfileByEmail(String email);
	public User getUserByMailID(String mailID);

	UserDTO userDTO = null;
	
	public UserDTO getUserProfile(UserProfileDTO userProfileDto);
	
	public int deleteCategory(UserProfileDTO userProfileDto);

}
