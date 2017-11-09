package com.nisum.portal.service.dto;

public class NotificationUserMappingDTO {

	private int id;

	private int notificationId;

	private String status;

	private String emaiId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getemaiId() {
		return emaiId;
	}

	public void setemaiId(String emaiId) {
		this.emaiId = emaiId;
	}

}
