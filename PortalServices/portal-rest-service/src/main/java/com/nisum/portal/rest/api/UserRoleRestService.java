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
import com.nisum.portal.util.ExceptionConstants;

@RestController
@RequestMapping("/v1/userrole")
public class UserRoleRestService {

	@Autowired
	UserRoleService userRoleService;

	private static Logger logger = LoggerFactory.getLogger(UserRoleRestService.class);



	//This method will add  User Role into database 
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes="application/json")
	public String addUserRole(@RequestBody UserRoleDTO userRoleDto) {	
		UserRole user =userRoleService.addUser(userRoleDto);
		if(user!=null) {
			return "User Role Added Successfully";
		}
		return "Failed to add UserRole";  
	}

	//This method will delete the existing user role from database
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String deleteUserRole(@PathVariable Integer id) {

		boolean status=userRoleService.deleteUser(id);
		if(status) {
			return "Given Record Successfully Deleted";
		}else {
			return "Given Record Does Not Exist"; 
		}
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
				return new ResponseEntity<>(ExceptionConstants.USERROLENOTEXISTS,HttpStatus.EXPECTATION_FAILED);
			}
			else
				return new ResponseEntity<>(ExceptionConstants.USERROLEUPDATED,HttpStatus.OK);
		} catch (Exception e) {
			throw new UserRoleServiceException(ExceptionConstants.INTERNALSERVERERROR);
		}

	}

	@ExceptionHandler(UserRoleServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors error = new Errors();
		error.setErrorCode("Errors -UsersRole");
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(error, HttpStatus.OK);
	}


}


