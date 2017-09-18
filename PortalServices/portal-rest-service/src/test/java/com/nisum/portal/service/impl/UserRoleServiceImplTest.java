package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;   
  
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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
	
	@Before
	public void init() {
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
}
