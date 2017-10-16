package com.nisum.portal.service.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.nisum.portal.data.domain.ProfileSettings;
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
	private String userName;
	private Timestamp loginDate;
	private UserRoleDTO role;
	private String activeStatus;
	private Timestamp createDate;
	private String image;
	private byte[] imageIcon;
	private String notifications;
	private String profileName;
	private Set<ProfileSettings> profileSettings=new HashSet<>();

	

	public Set<ProfileSettings> getProfileSettings() {
		return profileSettings;
	}

	public void setProfileSettings(Set<ProfileSettings> profileSettings) {
		this.profileSettings = profileSettings;
	}

	public byte[] getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(byte[] imageIcon) {
		this.imageIcon = imageIcon;
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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNotifications() {
		return notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

}
