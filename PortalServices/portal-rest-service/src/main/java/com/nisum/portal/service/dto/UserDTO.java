package com.nisum.portal.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.nisum.portal.data.domain.UserRole;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userId;
	private String emailId;
	private String name;
	private Timestamp loginDate;
	private UserRoleDTO roleId;
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
	public Timestamp getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}
	public UserRoleDTO getRoleId() {
		return roleId;
	}
	public void setRoleId(UserRoleDTO roleId) {
		this.roleId = roleId;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}



}
