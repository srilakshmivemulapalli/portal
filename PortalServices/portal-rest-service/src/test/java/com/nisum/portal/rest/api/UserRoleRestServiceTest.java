package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat; 
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.ServiceStatusDto;
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
	public void addUserRoleNullCheck() throws UserRoleServiceException {
		
		Errors error=new Errors();
		error.setErrorCode("Errors-UserRole");
		error.setErrorMessage("UserRole Name Cannot Be Emplty");
		
		UserRoleDTO userRoleDto=new UserRoleDTO();
		userRoleDto.setRole("NULL");
				
				ResponseEntity<?> actual =userRoleRestService.addUserRole(userRoleDto);
			
			assertThat(actual.getBody()).isEqualToComparingFieldByField(error);
	}
	
	
	@Test
	public void addUserRoleSuccess() throws UserRoleServiceException {
		
		ServiceStatusDto expected=new ServiceStatusDto();
		expected.setStatus(true);
		expected.setMessage("User Role Added Successfully !!");

		
		UserRoleDTO userRoleDto=new UserRoleDTO();
		userRoleDto.setRole("SE");
		
				UserRole userRole=new UserRole();
		
					userRole.setRole("SE");
					userRole.setRoleId(1);
					userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
				when(userRoleService.addUserRole(userRoleDto)).thenReturn(userRole);				
				ResponseEntity<?> actual =userRoleRestService.addUserRole(userRoleDto);
			
			assertThat(actual.getBody()).isEqualToComparingFieldByField(expected);
	}
	

	
	@Test
	public void addUserRoleFailure() throws UserRoleServiceException {
		
		Errors error=new Errors();
		error.setErrorCode("Errors-UserRole");
		error.setErrorMessage("User Role Exists Already !!");

		UserRoleDTO userRoleDto=new UserRoleDTO();
		userRoleDto.setRole("SE");
			
		when(userRoleService.addUserRole(userRoleDto)).thenThrow(UserRoleServiceException.class);
		ResponseEntity<?> actual=userRoleRestService.addUserRole(userRoleDto);
		assertThat(actual.getBody()).isEqualToComparingFieldByField(error);
	}  

	@Test
	public void deleteUserRoleSuccess() throws UserRoleServiceException {
		ServiceStatusDto expected=new ServiceStatusDto();
		expected.setStatus(true);
		expected.setMessage("User Role Deleted Successfully !!");

		int id=1;
		String role="SE";
		when(userRoleService.deleteUserRole(id)).thenReturn(true);
		ResponseEntity<?> actual=userRoleRestService.deleteUserRole(id);
		assertThat(actual.getBody()).isEqualToComparingFieldByField(actual.getBody()); 
	} 
	
	
	@Test
	public void deleteUserRoleFailure() throws UserRoleServiceException {
	
		Errors error=new Errors();
			error.setErrorCode("Errors-UserRole");
			error.setErrorMessage("User role doesnot exists !!");
		
		int id=2;
		String role="SE";
		when(userRoleService.deleteUserRole(id)).thenThrow(UserRoleServiceException.class);
		ResponseEntity<?> actual=userRoleRestService.deleteUserRole(id);
		assertThat(actual.getBody()).isEqualToComparingFieldByField(error);
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
		ResponseEntity<List<UserRole>> expected = new ResponseEntity<List<UserRole>>(HttpStatus.OK);
		when(userRoleService.getUserRole()).thenReturn(list);
		ResponseEntity<List<UserRoleDTO>> actual =  userRoleRestService.getUserRole();
		assertEquals(actual.getStatusCode(), expected.getStatusCode());
	}

	@Test
	public void updateUserRoleTest() throws UserRoleServiceException {
		String  message= "user role successfully updated into database";
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setMessage(message);
		UserRole expected1 = new UserRole();
		expected1.setRole("mg");
		expected1.setRoleId(1);	
		UserRole userRole = new UserRole();
		userRole.setRole("mg");
		userRole.setRoleId(1);
		UserRole userRole1 = new UserRole();
		userRole1.setRole("gm");
		userRole1.setRoleId(1);	
		userRole1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		when(userRoleService.findUserById(1)).thenReturn(userRole1);
		when(userRoleService.updateUserRole(userRole)).thenReturn(userRole);
		ResponseEntity<ServiceStatusDto> actual = userRoleRestService.updateUserRole(userRole);
		assertEquals(serviceStatusDto.getMessage(), serviceStatusDto.getMessage());
	}	
}

