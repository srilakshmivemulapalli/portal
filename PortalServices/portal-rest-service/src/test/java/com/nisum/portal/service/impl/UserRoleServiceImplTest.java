package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat; 
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.util.UserRoleServiceUtil;

@RunWith(PowerMockRunner.class) 
@PrepareForTest(UserRoleServiceUtil.class)
public class UserRoleServiceImplTest {

	@InjectMocks
	UserRoleServiceImpl userRoleServiceImpl=new UserRoleServiceImpl();
	
	@Mock
	UserRoleDAO userRoleDao;
	
	UserRole expected;
	List<UserRoleDTO> expected1;
	
	@Before
	public void setUp() {
		expected = new UserRole();
		expected.setRole("SE");
		expected.setRoleId(1);  
	}
	@Test
	public void addUserTest() {
	
		
			UserRoleDTO userRoleDto=new UserRoleDTO();
			userRoleDto.setRole("SE");
			
			UserRole userRole=new UserRole();
				userRole.setRole("SE");
				userRole.setRoleId(1);
			
			PowerMockito.mockStatic(UserRoleServiceUtil.class);
			PowerMockito.when(UserRoleServiceUtil.convertDtoToDao(userRoleDto)).thenReturn(userRole);
			
			
			when(userRoleDao.addUser(userRole)).thenReturn(userRole);
			
			UserRole actual=userRoleServiceImpl.addUser(userRoleDto);
			assertThat(actual).isEqualToComparingFieldByField(expected);   
	}

	@Test
	public void deleteUserTest() {
		int id = 1;
		when(userRoleDao.deleteUser(id)).thenReturn(true);

		assertTrue(userRoleServiceImpl.deleteUser(id));
}
	
	@Before
	public void init() {
		expected1 = new ArrayList<>();
		UserRoleDTO userRole = new UserRoleDTO();
		userRole.setRole("mg");
		userRole.setRoleId(1);
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		expected1.add(userRole);
	}


	@Test
	public void getUserRole() {
		List<UserRole> list = new ArrayList<>();
		UserRole userRole = new UserRole();
		userRole.setRoleId(1);
		userRole.setRole("mg");
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		list.add(userRole);
		when(userRoleDao.getUserRole()).thenReturn(list);

		List<UserRoleDTO> list1 = new ArrayList<>();
		UserRoleDTO userRoleDto = new UserRoleDTO();
		userRoleDto.setRoleId(1);
		userRoleDto.setRole("mg");
		userRoleDto.setCreatedDate(userRole.getCreatedDate());
		list1.add(userRoleDto);


		
		PowerMockito.mockStatic(UserRoleServiceUtil.class);
		PowerMockito.when(UserRoleServiceUtil.convertDaoTODto(list)).thenReturn(list1);
		List<UserRoleDTO> actual = userRoleServiceImpl.getUserRole();
		
		assertEquals(actual.size(), expected1.size());
	}

	@Test
	public void updateUserRole() {
		String message = "user role successfully updated into database";
		UserRole expected = new UserRole();
		expected.setRole("mg");
		expected.setRoleId(1);
		UserRole userRole = new UserRole();
		userRole.setRole("mg");
		userRole.setRoleId(1);
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		when(userRoleDao.updateUserRole(userRole)).thenReturn(userRole);
		UserRole actual = userRoleServiceImpl.updateUserRole(userRole);
		assertEquals(actual.getRole(), expected.getRole());
	}

}
