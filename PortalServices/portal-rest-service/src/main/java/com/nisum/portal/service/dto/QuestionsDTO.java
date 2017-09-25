package com.nisum.portal.service.dto;

import java.util.List;

public class QuestionsDTO {

	private List<QuestionariesDTO> questionDetails;
	private long totalQuestions;
	private long totalUsers;

	public List<QuestionariesDTO> getQuestionDetails() {
		return questionDetails;
	}
	public void setQuestionDetails(List<QuestionariesDTO> questionDetails) {
		this.questionDetails = questionDetails;
	}
	/**
	 * @return the totalQuestions
	 */
	public long getTotalQuestions() {
		return totalQuestions;
	}
	/**
	 * @param totalQuestions the totalQuestions to set
	 */
	public void setTotalQuestions(long totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	/**
	 * @return the totalUsers
	 */
	public long getTotalUsers() {
		return totalUsers;
	}
	/**
	 * @param totalUsers the totalUsers to set
	 */
	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}
	
}
