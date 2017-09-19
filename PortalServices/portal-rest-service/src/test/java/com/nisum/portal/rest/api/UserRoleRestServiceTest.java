package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.dto.UserRoleDTO;


@RunWith(MockitoJUnitRunner.class)
public class UserRoleRestServiceTest {

	@Mock
	UserRoleService userRoleService;
	
	@Mock
	UserRoleDAO userRoleDao;
	
	@InjectMocks
	UserRoleRestService userRoleRestService= new UserRoleRestService();
	
	
	@Test
	public void addUserRoleSuccess() {
		String expected="User Role Added Successfully";
		String temp=new String();
		
		UserRoleDTO userRoleDto=new UserRoleDTO();
		userRoleDto.setRole("SE");
		
				UserRole userRole=new UserRole();
		
					userRole.setRole("SE");
					userRole.setRoleId(1);
					userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			when(userRoleService.addUser(userRoleDto)).thenReturn(userRole);
			when(userRoleDao.addUser(userRole)).thenReturn(userRole);				
			String actual =userRoleRestService.addUserRole(userRoleDto);
			//assertEquals(temp, userRoleRestService.addUserRole(userRoleDto)); 
			assertEquals(expected, actual);
	}
	
	@Test
	public void addUserRoleFailure() {
		String expected="Failed to add UserRole";
		UserRoleDTO userRoleDto=new UserRoleDTO();
		
			
		//when(userRoleService.addUser(userRoleDto)).thenReturn(null);
		String actual=userRoleRestService.addUserRole(userRoleDto);
		assertEquals(expected, actual);
	}

	@Test
	public void deleteUserRoleSuccess() {
		String expected="Given Record Successfully Deleted";
		int id=1;
		when(userRoleService.deleteUser(id)).thenReturn(true);
		String actual=userRoleRestService.deleteUserRole(id);
		assertEquals(expected, actual); 
	}
	
	@Test
	public void deleteUserFailure() {
		String expected="Given Record Does Not Exist";
		
		int id=0;
		when(userRoleService.deleteUser(id)).thenReturn(false);
		String actual=userRoleRestService.deleteUserRole(id);
		assertEquals(expected, actual); 
	}
}
