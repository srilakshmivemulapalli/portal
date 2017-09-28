package com.nisum.portal.service.dto;

public class CountDTO {
	
	long questionCount;
	long userCount;
	public long getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(long questionCount) {
		this.questionCount = questionCount;
	}
	public long getUserCount() {
		return userCount;
	}
	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}
	@Override
	public String toString() {
		return "CountDTO [questionCount=" + questionCount + ", userCount=" + userCount + "]";
	}
	
	

}
