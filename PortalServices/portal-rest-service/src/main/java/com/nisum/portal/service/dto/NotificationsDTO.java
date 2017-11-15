package com.nisum.portal.service.dto;

import java.sql.Timestamp;

import com.nisum.portal.data.domain.Categories;

public class NotificationsDTO {
	private int notificationId;
	private String notificationType;
	private int notificationNavId;
	private Categories categoryId;
	private String emailId;
	private Timestamp createdDate;
	/**
	 * @return the id
	 */
	public int getNotificationId() {
		return notificationId;
	}
	/**
	 * @param id the id to set
	 */
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	/**
	 * @return the notificationType
	 */
	public String getNotificationType() {
		return notificationType;
	}
	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	/**
	 * @return the notificationNavId
	 */
	public int getNotificationNavId() {
		return notificationNavId;
	}
	/**
	 * @param notificationNavId the notificationNavId to set
	 */
	public void setNotificationNavId(int notificationNavId) {
		this.notificationNavId = notificationNavId;
	}
	/**
	 * @return the categoryId
	 */
	public Categories getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Categories categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + notificationId;
		result = prime * result + notificationNavId;
		result = prime * result + ((notificationType == null) ? 0 : notificationType.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationsDTO other = (NotificationsDTO) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (notificationId != other.notificationId)
			return false;
		if (notificationNavId != other.notificationNavId)
			return false;
		if (notificationType == null) {
			if (other.notificationType != null)
				return false;
		} else if (!notificationType.equals(other.notificationType))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotificationsDTO [notificationId=" + notificationId + ", notificationType=" + notificationType + ", notificationNavId="
				+ notificationNavId + ", categoryId=" + categoryId + ", emailId=" + emailId + ", createdDate="
				+ createdDate + "]";
	}
}
