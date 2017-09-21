package com.nisum.portal.service.dto;

public class UserRoleDTO {
	
	private Integer roleId;
	private String role;
	private String createdDate;
	/**
	 * 
	 * @return the roleId
	 */
	
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * 
	 * @param role the user role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * 
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * 
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
