package com.nisum.portal.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.nisum.portal.service.dto.UserRoleDTO;

/**
 * 
 * @author nisum
 *
 */
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int userId;
	private String emailId;
	private String name;
	private Timestamp loginDate;
	private UserRoleDTO role;
	private String activeStatus;

	/**
	 * Returns user id
	 * @return
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets user id
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Sets user email
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Returns user email
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Returns user name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets user created date
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns user created date
	 * @return
	 */
	public Timestamp getLoginDate() {
		return loginDate;
	}

	/**
	 * Sets user role
	 * @param loginDate
	 */
	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * Returns user role
	 * @return
	 */
	public UserRoleDTO getRole() {
		return role;
	}

	/**
	 * Sets role for a user
	 * @param role
	 */
	public void setRole(UserRoleDTO role) {
		this.role = role;
	}

	/**
	 * Returns user active status
	 * @return
	 */
	public String getActiveStatus() {
		return activeStatus;
	}

	/**
	 * Sets user active status
	 * @param activeStatus
	 */
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
}
