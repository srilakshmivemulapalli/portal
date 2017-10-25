package com.nisum.portal.data.dao.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.ProfileSettingsDAO;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.repository.UserProfileRepository;
import com.nisum.portal.data.repository.UserRepository;

@Configuration
public class ProfileSettingsDAOImpl implements ProfileSettingsDAO {

	private static Logger logger = LoggerFactory.getLogger(ProfileSettingsDAOImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	// ProfileSettings ps=new ProfileSettings();

	@Override
	public String updateProfile(User profile) {
		logger.info("ProfileSettingsDAOImpl :: updateProfile  ");

		if (userRepository.exists(profile.getUserId())) {
			userRepository.save(profile);
			return "Success";
		} else {
			return "Fail";
		}
	}

	@Override
	public User getUserByEmail(String email) {
		logger.info("BEGIN:ProfileSettingsDAOImpl :: getUserByEmail()");
		User user = null;
		user = userRepository.findByEmailId(email);
		logger.info("END:ProfileSettingsDAOImpl :: getUserByEmail()");
		return user;
	}

	@Override
	public User getUserProfile(User userId) {
		User user = userRepository.findByUserId(userId.getUserId());

		return user;
	}

	@Override
	public int deleteCategory(String emailId) {
		return userProfileRepository.deleteProfiles(emailId);
	}

	/*
	 * @Override public User deleteUserCategories(int userid) {
	 * 
	 * User profileSettings =(User) userProfileRepository.deleteByUserId(userid);
	 * return profileSettings; }
	 */

}
