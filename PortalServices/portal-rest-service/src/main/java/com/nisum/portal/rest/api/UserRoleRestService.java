package com.nisum.portal.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.dto.UserRoleDTO;

@RestController
@RequestMapping("/userrole")
public class UserRoleRestService {

	@Autowired
	UserRoleService userRoleService;
	
	
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
}
