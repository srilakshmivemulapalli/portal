package com.nisum.portal.service.dto;

import java.sql.Timestamp;

public class BlogsDTO {
	int blogsId;
	int categoryId;
	int userId;
	String description;
	String path;
	Timestamp createdDate;
	/**
	 * @return the blogsId
	 */
	public int getBlogsId() {
		return blogsId;
	}
	/**
	 * @param blogsId the blogsId to set
	 */
	public void setBlogsId(int blogsId) {
		this.blogsId = blogsId;
	}
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BlogsDTO [blogsId=" + blogsId + ", categoryId=" + categoryId + ", userId=" + userId + ", description="
				+ description + ", path=" + path + ", createdDate=" + createdDate + "]";
	}
	
	
}
