package com.nisum.portal.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NotificationUserMapping")
public class NotificationUserMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int notificationId;

	private String status;

	private String emailId;

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

	public String getEmail() {
		return emailId;
	}

	public void setEmail(String email) {
		this.emailId = email;
	}

	public NotificationUserMapping(int id, int notificationId, String status, String email) {
		super();
		this.id = id;
		this.notificationId = notificationId;
		this.status = status;
		this.emailId = email;
	}

	public NotificationUserMapping(int notificationId, String status, String email) {
		super();
		this.notificationId = notificationId;
		this.status = status;
		this.emailId = email;
	}

	@Override
	public String toString() {
		return "NotificationUserMapping [id=" + id + ", notificationId=" + notificationId + ", status=" + status
				+ ", emailId=" + emailId + "]";
	}

}
