package com.nisum.portal.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.nisum.portal.service.dto.UserRoleDTO;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userId;
	private String emailId;
	private String name;
	private Timestamp loginDate;
	private UserRoleDTO role;
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
	public UserRoleDTO getRole() {
		return role;
	}
	public void setRole(UserRoleDTO role) {
		this.role = role;
	}

	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
}
