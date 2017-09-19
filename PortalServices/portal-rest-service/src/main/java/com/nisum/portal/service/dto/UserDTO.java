package com.nisum.portal.service.dto;

import java.sql.Timestamp;

import com.nisum.portal.data.domain.UserRole;

public class UserDTO {
	
	private int userId;
	private String emailId;
	private String name;
	private Timestamp loginDate;
	private UserRole roleId;
	private String isActive;
	
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
	public UserRole getRoleId() {
		return roleId;
	}
	public void setRoleId(UserRole roleId) {
		this.roleId = roleId;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



}
