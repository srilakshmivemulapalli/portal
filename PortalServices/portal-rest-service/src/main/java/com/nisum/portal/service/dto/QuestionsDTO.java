package com.nisum.portal.service.dto;

import java.util.List;

public class QuestionsDTO {

	private List<QuestionariesDTO> dtos;
	private long totalQuestions;
	private long totalUsers;
	/**
	 * @return the dtos
	 */
	public List<QuestionariesDTO> getDtos() {
		return dtos;
	}
	/**
	 * @param dtos the dtos to set
	 */
	public void setDtos(List<QuestionariesDTO> dtos) {
		this.dtos = dtos;
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
