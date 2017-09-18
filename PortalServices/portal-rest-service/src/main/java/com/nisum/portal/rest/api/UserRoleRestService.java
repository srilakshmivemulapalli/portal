package com.nisum.portal.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.dto.UserRoleDTO;

@RestController
@RequestMapping("/userrole")
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
	
	 //This method is used for get the user details from Database
		@RequestMapping(value="/retrieve",method=RequestMethod.GET, produces="application/json")
		public List<UserRoleDTO> getUserRole()  {
			 logger.info("UserRoleService :: userrole");	 
			 return userRoleService.getUserRole();
		}
		
		//This method is used for update the user details into Database
		@RequestMapping(value="/update", method=RequestMethod.PUT, consumes="application/json",produces="application/json")
		public String updateUserRole(@RequestBody UserRole userRole) {				
			UserRole usr=userRoleService.updateUserRole(userRole);		
			if(usr!=null) {
				return "user role successfully updated into database";
			}
			    return "user role is not updated into database";
		
		}

	
	
}
