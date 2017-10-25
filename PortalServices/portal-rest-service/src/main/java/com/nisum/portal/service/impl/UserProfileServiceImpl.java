package com.nisum.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.ProfileSettingsDAO;
import com.nisum.portal.data.dao.api.UserDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserProfileService;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserProfileDTO;
import com.nisum.portal.util.UserServiceUtil;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	ProfileSettingsDAO profileSettingsDAO;

	@Autowired
	UserDAO userDAO;

	@Override
	public User getUserByMailID(String mailID) {
		return profileSettingsDAO.getUserByEmail(mailID);
	}

	private static Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	@Override
	public String updateUserProfile(UserDTO profile) {

		logger.info("UserProfileServiceImpl :: updateUserProfile :: Updating user Profilel");
		User user = UserServiceUtil.convertDtoObjectTODao(profile);

		return profileSettingsDAO.updateProfile(user);
	}

	/*
	 * @Override public UserProfileDTO getUserProfileById(int userId) { UserProfile
	 * user=profileSettingsDAO.getUserById(userId);
	 * 
	 * return UserServiceUtil.convertDaoObjectTODto(user);
	 * 
	 * }
	 */
	// @Override
	public UserDTO getUserProfileByEmail(String email) {
		logger.info("BEgin:getUserProfileByEmail in the" + this.getClass().getName());
		User user = profileSettingsDAO.getUserByEmail(email);

		return UserServiceUtil.convertDaoObjectToDto(user);
	}

	@Override
	public UserDTO getUserProfile(UserProfileDTO userProfileDto) {

		User userDetail = userDAO.findByEmailId(userProfileDto.getEmailId());
		// profileSettingsDAO.getUserProfile(userDetail);
		UserDTO userdto = UserServiceUtil.convertDaoObjectToDto(userDetail);
		return userdto;
	}

	@Override
	public int deleteCategory(UserProfileDTO userProfileDto) {

		return profileSettingsDAO.deleteCategory(userProfileDto.getEmailId());
	}

}
