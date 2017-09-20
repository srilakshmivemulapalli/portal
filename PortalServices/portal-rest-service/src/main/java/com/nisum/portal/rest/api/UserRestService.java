package com.nisum.portal.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping(value = "/v1/user")
public class UserRestService {
	
	private static Logger logger = LoggerFactory.getLogger(CategoriesRestService.class);
	
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
	public ResponseEntity<?> updateUserDetails(@RequestBody User user){
		logger.info("UserRestService ::  user update :::");
		userService.updateUserDetails(user);
		return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
	}
	@RequestMapping(value = "/updateUsers",method=RequestMethod.PUT,consumes = "application/json",produces="application/json")
	public ResponseEntity<?>  updateUsers(@RequestBody List<User> users){
		logger.info("UserRestService :: multiple users update :::");
		for(User user : users)
		{
			userService.updateUserDetails(user);
			}
         return new ResponseEntity<>("Users Updated Successfully",HttpStatus.OK);
	}
}
