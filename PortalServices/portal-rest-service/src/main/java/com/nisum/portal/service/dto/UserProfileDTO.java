package com.nisum.portal.service.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class UserProfileDTO {
	
  private String profileName;
  private String notifications;
  private List<Integer> profileSettings=new ArrayList<>();
  private String emailId;
  private String userName;
public String getProfileName() {
	return profileName;
}
public void setProfileName(String profileName) {
	this.profileName = profileName;
}
public String getNotifications() {
	return notifications;
}
public void setNotifications(String notifications) {
	this.notifications = notifications;
}
public List<Integer> getprofileSettings() {
	return profileSettings;
}
public void setprofileSettings(List<Integer> profileSettings) {
	this.profileSettings = profileSettings;
}

public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
  
  
  
}
