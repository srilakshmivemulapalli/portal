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
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.exception.UserServiceException;
import com.nisum.portal.util.ExceptionConstans;

@RestController
@RequestMapping(value = "/v1/user")
public class UserRestService {
	
	private static Logger logger = LoggerFactory.getLogger(CategoriesRestService.class);
	
	@Autowired
	UserService userService;
	
	/**
	 * Used to delete a single user
	 * @param userId
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/deleteUser/{userId}",method=RequestMethod.PUT,produces="application/json")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) throws UserServiceException{
		logger.info("UserRestService :: deleteUser :: Deleting User");
		try {
			User user = userService.findUserById(userId);
			if (user != null) {
				String activeStatus = user.getActiveStatus();
				if (activeStatus.equalsIgnoreCase("No")) {
					return new ResponseEntity<Object>(ExceptionConstans.ALREADYDELETED, HttpStatus.EXPECTATION_FAILED);
				} else {
					userService.deleteUser(userId);
					return new ResponseEntity<Object>(ExceptionConstans.USERDELETED, HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<Object>(ExceptionConstans.USERNOTEXISTS, HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception e) {
			logger.info("UserRestService :: deleteUser :: Internal Server Error");
			throw new UserServiceException(ExceptionConstans.INTERNALSERVERERROR, e);
		}
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
	public List<UserDTO> getUsers() throws UserServiceException {
		return userService.getUsers();
	}

	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public void updateUserDetails(@RequestBody User user) throws UserServiceException {
		userService.updateUserDetails(user);
	}
	/**
	 * Exception handler
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		logger.info("UserRestService :: exceptionHandler :: caught exception");
		Errors errors=new Errors();
		errors.setErrorCode("Error-User");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}
}
