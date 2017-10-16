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

import com.nisum.portal.data.domain.ProfileSettings;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserProfileService;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserProfileDTO;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserServiceException;
import com.nisum.portal.util.ExceptionConstans;
import com.nisum.portal.util.UserConstants;


@RestController
@RequestMapping(value = "/v1/userprofile")
public class UserProfileRestServices {
	
	@Autowired
	UserProfileService userProfileService;
	
	
	private static Logger logger = LoggerFactory.getLogger(UserProfileRestServices.class);
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateUser(@RequestBody UserProfileDTO userProfileDto) throws UserServiceException {
		logger.info("UserProfileRestService :: users profile::: update");
		try {
			
   		if(userProfileDto==null) {
			
			throw new UserServiceException(ExceptionConstans.INTERNALSERVERERROR);
		}
	User user=	userProfileService.getUserByMailID(userProfileDto.getEmailId());
	
	
	if(user!=null)
	{
		Set<ProfileSettings> profileSettingsSet=new HashSet<>();
		for(int cataogery:userProfileDto.getprofileSettings())
		{
			ProfileSettings ps=new ProfileSettings();
			ps.setCategoryId(cataogery);
			ps.setUserId(user.getUserId());
			profileSettingsSet.add(ps);
		}
		UserDTO userDto=new UserDTO();
		userDto.setActiveStatus(user.getActiveStatus());
		UserRoleDTO userRoleDTO=new UserRoleDTO();
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
		userDto.setProfileSettings(profileSettingsSet);
		userDto.setNotifications(userProfileDto.getNotifications());
		userDto.setProfileName(userProfileDto.getProfileName());
		userProfileService.updateUserProfile(userDto);
		System.out.println("notifications...."+"userId.."+userDto.getUserId()+"profile Name.."+userDto.getProfileName()+"categories.."+userDto.getProfileSettings());
		return new ResponseEntity<Object>(UserConstants.USERUPDATED, HttpStatus.OK);
		
	}
		
		//userProfileService.updateUserProfile(userDto);
		//System.out.println("notifications...."+"userId.."+userDto.getUserId()+"profile Name.."+userDto.getProfileName()+"categories.."+userDto.getProfileSettings());
		

		
		else
		{
			throw new UserServiceException(ExceptionConstans.USER_NOT_EXIST_WITHMAILID);	
		}
		}
		
		catch(Exception e)
		{
			logger.info("UserProfileRestService :: Update User Profile :: Internal Server Error");
			throw new UserServiceException(ExceptionConstans.INTERNALSERVERERROR, e);
			
			
		}
		
	
	}


}
		
			
