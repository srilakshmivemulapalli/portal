package com.nisum.portal.rest.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserServiceException;
import com.nisum.portal.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class UserRestServiceTest {
	
	@InjectMocks
	UserRestService userRestService;

	
	@Mock
	UserService userServiceMock;
	
	@Mock
	ServiceStatusDto serviceStatusDto;
	
	
	@Before
	public void init() {
		
	}
	@Test
	public void updateUser()
	{
		Date date = new Date();
		UserRoleDTO role = new UserRoleDTO();
		UserDTO user = new UserDTO();
		user.setUserId(1);
		user.setEmailId("test@test.com");
		user.setLoginDate(new Timestamp(date.getTime()));
		user.setActiveStatus("YES");
		user.setName("test");
		role.setRoleId(1);
		role.setRole("Admin");
		role.setCreatedDate(new Timestamp(date.getTime()));
		user.setRole(role);
		
		//when(userService.updateUserDetails(user)).thenReturn("Success");
		//when(userService.updateUserDetails(null)).thenReturn("Failed");
	//	assertEquals(new ResponseEntity(null),userRestService.updateUser(user));
	}
	
	@Test
	public void deleteUser() throws UserServiceException {
		
		int userId = 1;
		UserDTO dto = new UserDTO();
		dto.setActiveStatus("Yes");
		dto.setEmailId("dsdsdsdd");
		dto.setUserId(1);
		dto.setName("sasas");
		ServiceStatusDto expected = new ServiceStatusDto();
		expected.setMessage(Constants.USER_DELETED);
		expected.setStatus(true);
		ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(expected,HttpStatus.OK);
		when(userServiceMock.findUserById(userId)).thenReturn(dto.getActiveStatus());
		when(userServiceMock.deleteUser(userId)).thenReturn(1);
		ResponseEntity<ServiceStatusDto> actual = userRestService.deleteUser(userId);
		//assertThat(actual).is
		assertEquals(entity.getStatusCode(), actual.getStatusCode());
		
	}
	
	@Test
     public void TestUserNotFound() throws UserServiceException {
		
		int userId = 5;
		UserDTO dto = new UserDTO();
		dto.setActiveStatus("No");
		dto.setEmailId("dsdsdsdd");
		dto.setUserId(1);
		dto.setName("sasas");
		ServiceStatusDto expected = new ServiceStatusDto();
		expected.setMessage(Constants.USER_NOT_EXISTS);
		expected.setStatus(false);
		ResponseEntity<ServiceStatusDto> entity = new ResponseEntity<ServiceStatusDto>(expected,HttpStatus.EXPECTATION_FAILED);
		when(userServiceMock.findUserById(userId)).thenReturn(dto.getActiveStatus());
		when(userServiceMock.deleteUser(userId)).thenReturn(1);
		ResponseEntity<ServiceStatusDto> actual = userRestService.deleteUser(userId);
		assertEquals(entity.getStatusCode(), actual.getStatusCode());
		
	}
	
	@Test(expected=Exception.class)
	public void TestException2() throws UserServiceException
	{
		//List<UserDTO>users = null;
		
		when(userRestService.getUsers()).thenThrow(new UserServiceException(Constants.USERS_NOT_AVALIABLE));

		assertThat(userRestService.deleteUser(null));
		
		
	}
	@Test
	public void TestGetUsers() throws UserServiceException {
		UserDTO userDto = new UserDTO();
		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		userDto.setEmailId("dsdsds");
		userDto.setActiveStatus("Yes");
		userDto.setName("dsd");
		userDto.setUserId(1);
		dtoList.add(userDto);
	ResponseEntity<List<UserDTO>> resList=new ResponseEntity<List<UserDTO>>(dtoList, HttpStatus.OK);	
		Mockito.when(userServiceMock.getUsers()).thenReturn(dtoList);
		ResponseEntity<List<UserDTO>> actual = userRestService.getUsers();
		assertEquals(resList,actual);
	}

}
