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

import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserRoleServiceException;
import com.nisum.portal.util.Constants;

@RestController
@RequestMapping("/v1/userrole")
public class UserRoleRestService {
	@Autowired
	UserRoleService userRoleService;

	private static Logger logger = LoggerFactory.getLogger(UserRoleRestService.class);
 
	/**
	 * method that adds  User Role into database 
	 * @param userRoleDto
	 * @return
	 * @throws UserRoleServiceException
	 */
	//This 
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addUserRole(@RequestBody UserRoleDTO userRoleDto) throws UserRoleServiceException{	
		logger.info("UserRoleRestService :: Entered into addUserRole()");
		try {
				UserRole user =userRoleService.addUserRole(userRoleDto);
			
		}catch(Exception e) {	
			logger.error("UserRoleRestService :: User Role "+userRoleDto.getRole()+" exists already");
			
			Errors error = new Errors();
			error.setErrorCode("Errors-UserRole");
			error.setErrorMessage(Constants.USER_ROLE_EXISTS);
			ResponseEntity<Errors> rsEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
			return rsEntity;
		}
		logger.info("UserRoleRestService :: Given User Role Added Successfully");
		return new ResponseEntity<Object>(Constants.USER_ROLE_ADDED, HttpStatus.OK);
		
	}
	/**
	 *  method that deletes the existing user role from database
	 * @param id
	 * @return successful deletion of user role
	 * @throws UserRoleServiceException 
	 */

	@RequestMapping(value="/delete/{id}/{roleName}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserRole(@PathVariable Integer id, @PathVariable String roleName) throws UserRoleServiceException {
		logger.info("UserRoleRestService :: Entered into deleteUserRole()");
		
		try {
				userRoleService.deleteUserRole(id, roleName);	
		}catch(Exception e) {	
			logger.error("UserRoleRestService :: User Role with Given "+id+" Doesn't Exists");
			
			Errors error = new Errors();
			error.setErrorCode("Errors-UserRole");
			error.setErrorMessage(Constants.USERROLENOTEXISTS);
			ResponseEntity<Errors> rsEntity=new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
			return rsEntity;
		}	
		logger.info("UserRoleRestService :: Existing User Role Deleted Successfully");		
		return new ResponseEntity<Object>(Constants.USER_ROLE_DELETED, HttpStatus.OK);
	}

	/**
	 * get userRole
	 * @return
	 * @throws UserRoleServiceException
	 */
	@RequestMapping(value="/retrieve",method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<UserRoleDTO>> getUserRole()  throws UserRoleServiceException{
		logger.info("UserRoleService :: userrole::");	
		try {
			List<UserRoleDTO> userRoleList=userRoleService.getUserRole();

			if(userRoleList.isEmpty()) {	 		 
				return new ResponseEntity<List<UserRoleDTO>>(userRoleList,HttpStatus.NO_CONTENT);	     
			} else {
				return new ResponseEntity<List<UserRoleDTO>>(userRoleList,HttpStatus.OK);	
			}	
		} catch(Exception e) {
			throw new UserRoleServiceException("Error Message");
		} 
	}	 
	
	
	/**
	 * Updates userRole
	 * @param userRole
	 * @return
	 * @throws UserRoleServiceException
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT, consumes="application/json",produces="application/json")
	public ResponseEntity<String> updateUserRole(@RequestBody UserRole userRole) throws UserRoleServiceException{					
		logger.info("UserRoleService :: userrole");	 
		try {
			UserRole updateUserRole=userRoleService.updateUserRole(userRole);		
			if(updateUserRole==null) {
				return new ResponseEntity<>(Constants.USERROLENOTEXISTS,HttpStatus.EXPECTATION_FAILED);
			}
			else
				return new ResponseEntity<>(Constants.USERROLEUPDATED,HttpStatus.OK);
		} catch (Exception e) {
			throw new UserRoleServiceException(Constants.INTERNALSERVERERROR);
		}

	}
	
	/**
	 * Exception Handler
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UserRoleServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		System.out.println(ex.getMessage());
		Errors error = new Errors();
		error.setErrorCode("Errors -UserRole");
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(error, HttpStatus.OK);
	}
}

