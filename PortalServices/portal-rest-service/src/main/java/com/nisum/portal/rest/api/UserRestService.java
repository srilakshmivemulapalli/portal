package com.nisum.portal.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.exception.UserServiceException;

@RestController
@RequestMapping(value = "/User")
public class UserRestService {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/delete/{userIds}",method=RequestMethod.GET,produces="application/json")
	public String  deleteUser(@PathVariable List<Integer> userIds) throws UserServiceException{
		int successList = 0;
		int failedList = 0;
		int notFound = 0;
		String message = "";
		for (int userId : userIds) {
			User user = userService.findUserById(userId);
			if (user != null) {
				int deletedCount = userService.deleteUser(userId);
				if (deletedCount == 1) {
					successList++;
				} else {
					failedList++;
				}
			} else {
				notFound ++;
				message = notFound + " User doesnot exists in database";
			}
		}
		message = message + successList +" Users Deleted," + failedList + " Failed to delete";
		return message;
	}
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
	public List<UserDTO> getUsers() throws UserServiceException {
		return userService.getUsers();
	}

	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public void updateUserDetails(@RequestBody User user) throws UserServiceException {
		userService.updateUserDetails(user);
	}
}
