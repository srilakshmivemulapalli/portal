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
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserRoleServiceException;
import com.nisum.portal.util.Constants;

@RestController
@RequestMapping("/v1/userrole")
public class UserRoleRestService {

	@Autowired
	UserRoleService userRoleService;

	private static Logger logger = LoggerFactory.getLogger(UserRoleRestService.class);



	//This method will add  User Role into database 
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> addUserRole(@RequestBody UserRoleDTO userRoleDto) throws UserRoleServiceException {	
		UserRole user =userRoleService.addUserRole(userRoleDto);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(user!=null) {
			serviceStatusDto.setMessage(Constants.USER_ROLE_ADDED);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
			
		}
		serviceStatusDto.setMessage(Constants.USER_ROLE_NOT_EXISTS);
		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.EXPECTATION_FAILED);
	}

	//This method will delete the existing user role from database
	@RequestMapping(value="/delete/{id}/{rolename}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserRole(@PathVariable Integer id, @PathVariable String roleName) throws UserRoleServiceException {

		boolean status=userRoleService.deleteUserRole(id, roleName);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		if(status) {
			serviceStatusDto.setMessage(Constants.USER_ROLE_DELETED);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
		}else {
			serviceStatusDto.setMessage(Constants.USER_ROLE_NOT_EXIST);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.EXPECTATION_FAILED);
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
	public ResponseEntity<ServiceStatusDto> updateUserRole(@RequestBody UserRole userRole) throws UserRoleServiceException{					
		logger.info("UserRoleService :: userrole");	 
		try {
			Integer roleId=userRole.getRoleId();
			Integer userRoleId=userRoleService.findUserById(roleId);
			ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
			if(userRoleId!=null) {
				userRoleService.updateUserRole(userRole);	
				serviceStatusDto.setMessage(Constants.USER_ROLE_UPDATED);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);

			} else {
				serviceStatusDto.setMessage(Constants.USER_ROLE_NOT_EXISTS);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.EXPECTATION_FAILED);


			}
		} catch (Exception e) {
			throw new UserRoleServiceException(Constants.INTERNALSERVERERROR);
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


