package com.nisum.portal.service.dto;

public class ReplyQuestionDTO {
	
	private Integer questionId;
	private String emailId;
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getReplyDescription() {
		return replyDescription;
	}
	public void setReplyDescription(String replyDescription) {
		this.replyDescription = replyDescription;
	}
	private String replyDescription;
	
}
