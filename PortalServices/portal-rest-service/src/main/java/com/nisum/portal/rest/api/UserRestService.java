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

import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.exception.QuestionariesServiceException;
import com.nisum.portal.service.exception.UserServiceException;
import com.nisum.portal.util.ExceptionConstants;
import com.nisum.portal.util.UserConstants;

@RestController
@RequestMapping(value = "/v1/user")
public class UserRestService {
	
	private static Logger logger = LoggerFactory.getLogger(CategoriesRestService.class);
	
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/deleteUser/{userId}",method=RequestMethod.PUT,produces="application/json")
	public ResponseEntity<ServiceStatusDto> deleteUser(@PathVariable Integer userId) throws UserServiceException{
		logger.info("UserRestService :: deleteUser :: Deleting User");
		try {
			String activeStatus = userService.findUserById(userId);
			ServiceStatusDto serviceStatusDTO = new ServiceStatusDto();
			if (activeStatus == null || activeStatus.equalsIgnoreCase("No")) {
				serviceStatusDTO.setMessage(ExceptionConstants.USERNOTEXISTS);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDTO, HttpStatus.EXPECTATION_FAILED);
			} else {
				userService.deleteUser(userId);
				serviceStatusDTO.setMessage(ExceptionConstants.USERDELETED);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("UserRestService :: deleteUser :: Internal Server Error");
			throw new UserServiceException(ExceptionConstants.INTERNALSERVERERROR, e);
		}
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
     public ResponseEntity<List<UserDTO>>  getUsers() throws UserServiceException {
		//return userService.getUsers();
		logger.info("UserRestService :: users");
		List<UserDTO> users = userService.getUsers();
		if (users.isEmpty()) {
			// new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
			throw new UserServiceException(UserConstants.USERLISTEMPTY);
			// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDto) throws UserServiceException {
		logger.info("UserRestService :: users::: update");
		try {
		if(userDto==null) {
			throw new UserServiceException(ExceptionConstants.INTERNALSERVERERROR);
		}
		userService.updateUserDetails(userDto);
		return new ResponseEntity<Object>(UserConstants.USERUPDATED, HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.info("UserRestService :: UpdateUser :: Internal Server Error");
			throw new UserServiceException(ExceptionConstants.INTERNALSERVERERROR, e);
		}
	}
	@RequestMapping(value = "/updateUsers",method=RequestMethod.PUT,consumes = "application/json",produces="application/json")
	public ResponseEntity<Object>  updateUsers(@RequestBody List<UserDTO> usersDTO) throws UserServiceException{
		logger.info("UserRestService :: multiple users update :::");
		try {
			for(UserDTO userDto : usersDTO)
			{
			userService.updateUserDetails(userDto);
			}
         return new ResponseEntity<>("Users Updated Successfully",HttpStatus.OK);
	}
		catch(Exception e)
		{
			logger.info("UserRestService :: Update multiple Users :: Internal Server Error");
			throw new UserServiceException(ExceptionConstants.INTERNALSERVERERROR, e);
		}
	}
	
	/**
	 * getUserCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieveCount", method = RequestMethod.GET)
	public Object retrieveCount() throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: getUserCount");
		return userService.getUserCount();
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
