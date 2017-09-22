package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserRoleServiceException;


@RunWith(MockitoJUnitRunner.class)
public class UserRoleRestServiceTest {

    @Mock
	UserRoleService userRoleService;
	
	@Mock
	UserRoleDAO userRoleDao;
	
	@InjectMocks
	UserRoleRestService userRoleRestService= new UserRoleRestService();
	
	List<UserRoleDTO> expected;

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
	@Before
	public void init() {
		expected = new ArrayList<>();
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setRole("mg");
		userRoleDTO.setRoleId(1);
		expected.add(userRoleDTO);
	}
	
	
	
	@Test
	public void getUserRoleTest() throws UserRoleServiceException {
		List<UserRoleDTO> list = new ArrayList<>();
		UserRoleDTO userRoleDto = new UserRoleDTO();
		userRoleDto.setRoleId(1);
		userRoleDto.setRole("mg");
		list.add(userRoleDto);
		when(userRoleService.getUserRole()).thenReturn(list);
		ResponseEntity<List<UserRoleDTO>> actual =  userRoleRestService.getUserRole();
		assertEquals(actual, expected.size());
	}

	@Test
	public void updateUserRoleTest() throws UserRoleServiceException {
		String  message= "user role successfully updated into database";
		UserRole expected1 = new UserRole();
		expected1.setRole("mg");
		expected1.setRoleId(1);	
		UserRole userRole = new UserRole();
		userRole.setRole("mg");
		userRole.setRoleId(1);
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		when(userRoleService.updateUserRole(userRole)).thenReturn(userRole);
		ResponseEntity<String> actual = userRoleRestService.updateUserRole(userRole);
		assertEquals(actual, message);
	}

	
}


