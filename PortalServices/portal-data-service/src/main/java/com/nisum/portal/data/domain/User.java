package com.nisum.portal.data.domain;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "userId")
	private int userId;
	@Column (name = "emailid")
	private String emailId;
	private String name;
	private Timestamp loginDate;
	private String activeStatus;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
	private UserRole role;

	/**
	 * Returns User role
	 * @return
	 */
	public UserRole getRole() {
		return role;
	}
	
	/**
	 * Sets user role for a user
	 * @param role
	 */
	public void setRole(UserRole role) {
		this.role= role;
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

	/**
	 * Returns user email id
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * Sets user email id
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
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
	 * Returns email id
	 * @return
	 */
	public String getemailId() {
		return emailId;
	}
	
	/**
	 * Returns user created date
	 * @return
	 */
	public Timestamp getLoginDate() {
		return loginDate;
	}
	
	/**
	 * Sets user created date
	 * @param loginDate
	 */
	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}
	
	/**
	 * Returns user name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets user name 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", name=" + name + ", loginDate=" + loginDate
				+ ", activeStatus=" + activeStatus + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeStatus == null) ? 0 : activeStatus.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((loginDate == null) ? 0 : loginDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (activeStatus == null) {
			if (other.activeStatus != null)
				return false;
		} else if (!activeStatus.equals(other.activeStatus))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (loginDate == null) {
			if (other.loginDate != null)
				return false;
		} else if (!loginDate.equals(other.loginDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}