package com.nisum.portal.dao.impl;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

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
	
	@Before
	public void init() {
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
			UserRole actual=userRoleDaoImpl.addUser(userRole);
			
			assertEquals(expected.getRole(), actual.getRole());		
	}
	
	
	@Test
	public void deleteUser() {
		int id=1;
		assertTrue(userRoleDaoImpl.deleteUser(id));
	}
	
	@After
	public void cleanUp() {
		expected=null;
	}
}
