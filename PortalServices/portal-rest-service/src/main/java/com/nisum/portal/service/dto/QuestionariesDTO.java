package com.nisum.portal.service.dto;

import java.sql.Timestamp;
import java.util.List;


public class QuestionariesDTO {

	
	private Integer questionId;
	private String question;
	private String description;
	private Timestamp createdDate;
	
	private String categoryName;
	private String emailId;
	private String displayName;
	
	private String displayImage;
	
	private Integer questionRepliesCount;
	
	private List<QuestionariesCommentsDTO>  questionComments;


	/**
	 * @return the questionComments
	 */
	public List<QuestionariesCommentsDTO> getQuestionComments() {
		return questionComments;
	}
	/**
	 * @param questionComments the questionComments to set
	 */
	public void setQuestionComments(List<QuestionariesCommentsDTO> questionComments) {
		this.questionComments = questionComments;
	}
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
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
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
	public String getDisplayImage() {
		return displayImage;
	}
	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionariesDTO [questionId=" + questionId + ", question=" + question + ", description=" + description
				+ ", createdDate=" + createdDate + ", categoryName=" + categoryName + ", emailId=" + emailId
				+ ", displayName=" + displayName + ", displayImage=" + displayImage + ", questionRepliesCount="
				+ questionRepliesCount + ", questionComments=" + questionComments + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((displayImage == null) ? 0 : displayImage.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((questionComments == null) ? 0 : questionComments.hashCode());
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + ((questionRepliesCount == null) ? 0 : questionRepliesCount.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		if (displayImage == null) {
			if (other.displayImage != null)
				return false;
		} else if (!displayImage.equals(other.displayImage))
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
		if (questionComments == null) {
			if (other.questionComments != null)
				return false;
		} else if (!questionComments.equals(other.questionComments))
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (questionRepliesCount == null) {
			if (other.questionRepliesCount != null)
				return false;
		} else if (!questionRepliesCount.equals(other.questionRepliesCount))
			return false;
		return true;
	}
}
