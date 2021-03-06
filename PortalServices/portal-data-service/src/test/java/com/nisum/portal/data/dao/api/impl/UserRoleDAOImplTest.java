package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.UserRoleDAOImpl;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.data.repository.UserRoleRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleDAOImplTest {

	@InjectMocks
	UserRoleDAOImpl userRoleDaoImpl=new UserRoleDAOImpl();
	
	@Mock
	UserRoleRepository userRoleRepository;
	UserRole expected;
	
	List<UserRole> expected1;
	
	@Before
	public void setUP() {
		expected=new UserRole();
		expected.setRoleId(1);
		expected.setRole("SE");
		expected.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
	}
	
	@Test
	public void addUser() {
		
		UserRole userRole=new UserRole();
			userRole.setRoleId(1);
			userRole.setRole("SE");
			userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));

			when(userRoleRepository.save(userRole)).thenReturn(userRole);
			UserRole actual=userRoleDaoImpl.addUserRole(userRole);
			
			assertEquals(expected.getRole(), actual.getRole());		
	}
	
	
	@Test
	public void deleteUser() {
		int id=1;
		assertTrue(userRoleDaoImpl.deleteUserRole(id));
	}
	
	@After
	public void cleanUp() {
		expected=null;
	}
	
	@Before
	public void setUp() {
		expected1 = new ArrayList<>();
		UserRole userRole = new UserRole();
		userRole.setRole("mg");
		userRole.setRoleId(1);
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		expected1.add(userRole);
	}
	
	@Test
	public void getUserRoleTest() {
		List<UserRole> list = new ArrayList<>();
		UserRole userRole = new UserRole();
		userRole.setRoleId(1);
		userRole.setRole("mg");
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		list.add(userRole);
		when(userRoleRepository.findAll()).thenReturn(list);
		List<UserRole> actual =  userRoleDaoImpl.getUserRole();
		assertEquals(actual.size(), expected1.size());
	}

	@Test
	public void updateUserRoleTest() {
		String  message= "user role successfully updated into database";
		UserRole expected1 = new UserRole();
		expected1.setRole("mg");
		expected1.setRoleId(1);	
		UserRole userRole = new UserRole();
		userRole.setRole("mg");
		userRole.setRoleId(1);
		userRole.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		when(userRoleRepository.findOne(userRole.getRoleId())).thenReturn(userRole);
		when(userRoleRepository.save(userRole)).thenReturn(userRole);
		UserRole actual = userRoleDaoImpl.updateUserRole(userRole);
		assertEquals(actual.getRole(), expected1.getRole()); 
	}

	
}

