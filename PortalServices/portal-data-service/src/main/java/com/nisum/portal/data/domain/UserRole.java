package com.nisum.portal.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserRole")
public class UserRole implements Serializable{

private static final long serialVersionUID = 1L;

@Id
@Column(name = "roleId")
private int roleId;

private String role;
private Timestamp createdDate;

public int getRoleId() {
return roleId;
}

public void setRoleId(int roleId) {
this.roleId = roleId;
}

public String getRole() {
return role;
}

public void setRole(String role) {
this.role = role;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp date) {
this.createdDate = date;
}

//@Override
//public String toString() {
//return "UserRole [roleId=" + roleId + ", role=" + role + ", createdDate=" + createdDate + "]";
//}

@Override
public int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
result = prime * result + ((role == null) ? 0 : role.hashCode());
result = prime * result + roleId;
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
UserRole other = (UserRole) obj;
if (createdDate == null) {
if (other.createdDate != null)
return false;
} else if (!createdDate.equals(other.createdDate))
return false;
if (role == null) {
if (other.role != null)
return false;
} else if (!role.equals(other.role))
return false;
if (roleId != other.roleId)
return false;
return true;
}

}
