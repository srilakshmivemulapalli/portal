package com.nisum.portal.rest.api;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.ProfileSetting;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserProfileService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ProfileSettingDto;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserProfileDTO;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserServiceException;
import com.nisum.portal.util.ExceptionConstans;

@RestController
@RequestMapping(value = "/v1/userprofile/")
public class UserProfileRestServices {

	@Autowired
	UserProfileService userProfileService;

	private static Logger logger = LoggerFactory.getLogger(UserProfileRestServices.class);

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UserProfileDTO userProfileDto) throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: update");
		try {

			if (userProfileDto == null) {

				throw new UserServiceException(ExceptionConstans.INTERNALSERVERERROR);
			}
			User user = userProfileService.getUserByMailID(userProfileDto.getEmailId());
			if (user != null) {
				Set<Integer> userCategories = new HashSet<Integer>();

				if (user.getProfileSettings() == null || user.getProfileSettings().size() == 0) {

				} else {
					for (ProfileSetting s : user.getProfileSettings()) {

						userCategories.add(s.getCategoryId().getCategoryId());
					}
				}

				UserDTO userDto = new UserDTO();
				userDto.setActiveStatus(user.getActiveStatus());
				UserRoleDTO userRoleDTO = new UserRoleDTO();
				userRoleDTO.setRole(user.getRole().getRole());
				userRoleDTO.setRoleId(user.getRole().getRoleId());
				userRoleDTO.setCreatedDate(user.getCreateDate());
				userDto.setRole(userRoleDTO);
				userDto.setCreateDate(user.getCreateDate());
				userDto.setImage(user.getImage());
				userDto.setLoginDate(user.getLoginDate());
				userDto.setUserId(user.getUserId());
				userDto.setUserName(user.getUserName());
				userDto.setEmailId(user.getEmailId());

				// ========================= START =============================

				Set<ProfileSettingDto> profileSettings = new HashSet<>();

				for (Integer i : userProfileDto.getprofileSettings()) {
					ProfileSettingDto profile = new ProfileSettingDto();
					CategoriesDTO category = new CategoriesDTO();
					category.setCategoryId(i);

					UserDTO userId = new UserDTO();
					userId.setUserId(user.getUserId());

					profile.setCategoryId(category);
					profile.setUserId(userId);
					profileSettings.add(profile);
				}

				// ========================== END ============================

				userProfileService.deleteCategory(userProfileDto);

				userDto.setProfileSettings(profileSettings);

				userDto.setNotifications(userProfileDto.getNotifications());
				userDto.setProfileName(userProfileDto.getProfileName());
				userProfileService.updateUserProfile(userDto);

				return new ResponseEntity<UserProfileDTO>(userProfileDto, HttpStatus.OK);

			}

			else {
				throw new UserServiceException(ExceptionConstans.USER_NOT_EXIST_WITHMAILID);
			}
		}

		catch (Exception e) {
			logger.info("UserProfileRestService :: Update User Profile :: Internal Server Error");
			throw new UserServiceException(ExceptionConstans.INTERNALSERVERERROR, e);

		}

	}

	@RequestMapping(value = "/getProfile", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserDTO> getUserProfile(@RequestBody UserProfileDTO userProfileDto)
			throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: get");

		UserDTO userdto = userProfileService.getUserProfile(userProfileDto);

		return new ResponseEntity<UserDTO>(userdto, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteCategory(@RequestBody UserProfileDTO userProfileDto) throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: get");

		// System.out.println("getUserId..."+userProfileDto.getUserId());
		Integer userdto = userProfileService.deleteCategory(userProfileDto);

		return new ResponseEntity<Integer>(userdto, HttpStatus.OK);

	}

}
