package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.UserDAOImpl;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.data.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOImplTest {
	
	@Mock
	User user;
	
	@Mock
	UserRole userRole;
	
	@InjectMocks
	UserDAOImpl userDAOImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Test
	public void updateUser() 
	{
		Date date = new Date();
		String s = "Success";
		UserRole role = new UserRole();
		User user = new User();
		user.setUserId(1);
		user.setEmailId("test@test.com");
		user.setLoginDate(new Timestamp(date.getTime()));
		user.setActiveStatus("YES");
		user.setName("test");
		role.setRoleId(1);
		role.setRole("Admin");
		role.setCreatedDate(new Timestamp(date.getTime()));
		user.setRole(role);
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.save(user)).thenReturn(null);
		assertEquals(user,userDAOImpl.updateUser(user));
		}
	
	@Test
	public void deleteUser() {
		int userId = 1;
		when(userRepository.deleteUser(userId)).thenReturn(1);
		int actual = userDAOImpl.deleteUser(userId);
		assertEquals(userId,actual);
	}
	
	@Test
	public void findUserById() {
		int userId = 1;
		User user = new User();
		user.setActiveStatus("Yes");
		user.setEmailId("sdsd");
		user.setName("dsdsd");
		user.setUserId(1);
		when(userRepository.findOne(userId)).thenReturn(user);
		User actual = userDAOImpl.findUserById(userId);
		assertEquals(user, actual);
	}
	@Test
	public void test_FindAllUserDAOImpl() {
		User user=new User();
		List<User> userList=new ArrayList<User>();
		 // UserRole role=new UserRole();
		 // role.setRole(role);
		user.setEmailId("radhikapujari@nisum.com");
		user.setActiveStatus("yes");
		user.setName("radhika");
		//user.setRole(role.setRole("admin"));
		userList.add(user);
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		List<User>	actual=userDAOImpl.getUsers(); 
		
		assertEquals(userList,actual);
		
		
		
	}
	
	
}
