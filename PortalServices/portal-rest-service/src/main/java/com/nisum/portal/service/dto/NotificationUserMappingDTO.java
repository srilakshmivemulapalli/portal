package com.nisum.portal.service.dto;

public class NotificationUserMappingDTO {

	private int id;

	private int notificationId;

	private String status;

	private String emaiId;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the notificationId
	 */
	public int getNotificationId() {
		return notificationId;
	}

	/**
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the emaiId
	 */
	public String getEmaiId() {
		return emaiId;
	}

	/**
	 * @param emaiId the emaiId to set
	 */
	public void setEmaiId(String emaiId) {
		this.emaiId = emaiId;
	}
}
