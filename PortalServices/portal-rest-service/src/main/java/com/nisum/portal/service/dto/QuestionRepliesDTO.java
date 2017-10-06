package com.nisum.portal.service.dto;

import java.sql.Timestamp;

public class QuestionRepliesDTO {

	private int replyId;
	private String replyDescription;
	private Timestamp updatedDate;
	private String emailId;
	private String displayImage;
	
	public String getDisplayImage() {
		return displayImage;
	}
	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}
	/**
	 * @return the replyId
	 */
	public int getReplyId() {
		return replyId;
	}
	/**
	 * @param replyId the replyId to set
	 */
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	/**
	 * @return the replyDescription
	 */
	public String getReplyDescription() {
		return replyDescription;
	}
	/**
	 * @param replyDescription the replyDescription to set
	 */
	public void setReplyDescription(String replyDescription) {
		this.replyDescription = replyDescription;
	}
	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}
