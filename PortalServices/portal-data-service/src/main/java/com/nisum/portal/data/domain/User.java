package com.nisum.portal.data.domain;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "User")
public class User {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "userId")
	private String userid;
	private String emailid;
	private String name;
	private Timestamp loginDate;
	
	@ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "roleId")
	private UserRole role;
	
public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role= role;
	}
	//	public Set<UserRole> getRoleId() {
//		return roleId;
//	}
//	public void setRoleId(Set<UserRole> roleId) {
//		this.roleId = roleId;
//	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", emailid=" + emailid + ", name=" + name + ", loginDate=" + loginDate
				+ ", roleId=" + role + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + ((loginDate == null) ? 0 : loginDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
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
			if (other.role!= null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
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
//	public Date getLoginDate() {
//		return loginDate;
//	}
//	public void setLoginDate(Date loginDate) {
//		this.loginDate = loginDate;
//	}
//	public UserRole getRoleId() {
//		return roleId;
//	}
//	public void setRoleId(UserRole roleId) {
//		this.roleId = roleId;
//	}
	
	

}
