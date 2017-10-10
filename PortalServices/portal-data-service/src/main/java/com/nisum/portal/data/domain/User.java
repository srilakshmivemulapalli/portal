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
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "userId")
	private int userId;
	@Column(name = "emailid")
	private String emailId;
	private String name;
	private Timestamp loginDate;
	private String activeStatus;
	private Timestamp createDate;
	private String image;
	private byte[] imageIcon;

	public byte[] getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(byte[] imageIcon) {
		this.imageIcon = imageIcon;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
	private UserRole role;

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

	public String getActiveStatus() {
		return activeStatus;
	}

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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeStatus == null) ? 0 : activeStatus.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
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
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", name=" + name + ", loginDate=" + loginDate
				+ ", activeStatus=" + activeStatus + ", createDate=" + createDate + ", image=" + image + ", role="
				+ role + "]";
	}

	public User(int userId, String emailId, String name, Timestamp loginDate, String activeStatus, Timestamp createDate,
			String image, UserRole role) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.name = name;
		this.loginDate = loginDate;
		this.activeStatus = activeStatus;
		this.createDate = createDate;
		this.image = image;
		this.role = role;
	}

	public User(int userId) {
		this.userId = userId;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
}