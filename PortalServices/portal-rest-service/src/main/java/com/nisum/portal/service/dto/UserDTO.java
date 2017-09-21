package com.nisum.portal.service.dto;

import java.io.Serializable;
import com.nisum.portal.service.dto.UserRoleDTO;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userId;
	private String emailId;
	private String name;
	private String loginDate;
	private UserRoleDTO userRole;
	private String activeStatus;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public UserRoleDTO getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoleDTO userRole) {
		this.userRole = userRole;
	}

	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
}
