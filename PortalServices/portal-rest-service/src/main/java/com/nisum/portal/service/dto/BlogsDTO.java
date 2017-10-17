package com.nisum.portal.service.dto;

import java.sql.Timestamp;
import java.util.List;

public class BlogsDTO {
	int blogsId;
	String title;
	int userId;
	String description;
	String path;
	Timestamp createdDate;
	String userMailId;
	List<String> fileNames;
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
	/**
	 * @return the userMailId
	 */
	public String getUserMailId() {
		return userMailId;
	}
	/**
	 * @param userMailId the userMailId to set
	 */
	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getFileNames() {
		return fileNames;
	}
	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}
	@Override
	public String toString() {
		return "BlogsDTO [blogsId=" + blogsId + ", title=" + title + ", userId=" + userId + ", description="
				+ description + ", path=" + path + ", createdDate=" + createdDate + ", userMailId=" + userMailId
				+ ", fileNames=" + fileNames + "]";
	}
	
	
}
