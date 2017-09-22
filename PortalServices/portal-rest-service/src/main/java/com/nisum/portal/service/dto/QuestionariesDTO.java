package com.nisum.portal.service.dto;

import java.sql.Timestamp;


public class QuestionariesDTO {

	
	private Long questionId;
	private String question;
	private String description;
	private Timestamp createdDate;
	
	private String categoryName;
	private String emailId;
	private String displayName;
	
	private Integer questionRepliesCount;
	
	/**
	 * @return the questionRepliesCount
	 */
	public Integer getQuestionRepliesCount() {
		return questionRepliesCount;
	}
	/**
	 * @param questionRepliesCount the questionRepliesCount to set
	 */
	public void setQuestionRepliesCount(Integer questionRepliesCount) {
		this.questionRepliesCount = questionRepliesCount;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = (int) (prime * result + questionId);
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
		QuestionariesDTO other = (QuestionariesDTO) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QuestionariesDTO [questionId=" + questionId + ", question=" + question + ", description=" + description
				+ ", createdDate=" + createdDate + ", categoryName=" + categoryName
				+ ", emailId=" + emailId + ", displayName=" + displayName + "]";
	}
	
}
