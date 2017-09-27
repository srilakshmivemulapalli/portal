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
import com.nisum.portal.data.repository.UserRoleRepository;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.UserRoleServiceException;
import com.nisum.portal.util.UserRoleCache;
import com.nisum.portal.util.UserRoleServiceUtil;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class) 
@PrepareForTest(UserRoleServiceUtil.class)
public class UserRoleServiceImplTest {

	@InjectMocks
	UserRoleServiceImpl userRoleServiceImpl=new UserRoleServiceImpl();
	
	@Mock
	UserRoleDAO userRoleDao;
	
	@Mock
	UserRoleRepository userRoleRepository;
	
	@Mock
	UserRoleCache cache; 
	 
	UserRole expected;
	List<UserRoleDTO> expected1;
	
	@Before
	public void setUp() {
		expected = new UserRole();
		expected.setRole("SE");
		expected.setRoleId(1);    
	}
	
	@Test
	public void addUserRoleSuccessTest() throws UserRoleServiceException {
			UserRoleDTO userRoleDto=new UserRoleDTO();
			userRoleDto.setRole("SE");
			
			UserRole userRole=new UserRole();
				userRole.setRole("SE");
				userRole.setRoleId(1);
		
				
				//when(cache.verifyUserRoleToCache(userRoleDto)).thenReturn(true);
				
		 
			when(cache.verifyUserRoleToCache(userRoleDto)).thenReturn(true);
			//PowerMockito.mockStatic(UserRoleServiceUtil.class); 
			//PowerMockito.when(UserRoleServiceUtil.convertDtoToDao(userRoleDto)).thenReturn(userRole);
			when(userRoleRepository.save(userRole)).thenReturn(userRole);
			when(userRoleDao.addUserRole(userRole)).thenReturn(userRole); 
			
			
			//when(userRoleServiceImpl.addUserRole(userRoleDto)).thenReturn(userRole);
	
			 UserRole actual = userRoleServiceImpl.addUserRole(userRoleDto);
			
			assertThat(actual).isEqualToComparingFieldByField(expected);     
	}
	

	@Test(expected=UserRoleServiceException.class)
	public void addUserRoleFailureTest() throws UserRoleServiceException {
			UserRoleDTO userRoleDto=new UserRoleDTO();
			userRoleDto.setRole("SE");
		
			when(cache.verifyUserRoleToCache(userRoleDto)).thenReturn(false);
			userRoleServiceImpl.addUserRole(userRoleDto);   
	}
	
	

	@Test
	public void deleteUserRoleSuccessTest() throws UserRoleServiceException {
		Integer id = 1;
		String roleName="SE";
		when(cache.findUserRole(id)).thenReturn(true);
		when(userRoleDao.deleteUserRole(id)).thenReturn(true);
		assertTrue(userRoleServiceImpl.deleteUserRole(id));
	}
	
	
	@Test(expected=UserRoleServiceException.class)
	public void deleteUserRoleFailureTest() throws UserRoleServiceException {
		Integer id = 0;
		String roleName="SE";
		when(cache.findUserRole(id)).thenReturn(false);
		userRoleServiceImpl.deleteUserRole(id);
	}
	@Before
	public void init() {
		expected1 = new ArrayList<>();
		UserRoleDTO userRole = new UserRoleDTO();
		userRole.setRole("mg");
		userRole.setRoleId(1);
		
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
