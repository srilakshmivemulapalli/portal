package com.nisum.portal.service.dto;

import java.sql.Timestamp;

public class QuestionariesCommentsDTO {

	private Integer commentId;
	private int questionsId;
	private Timestamp createdDate;
	private String emailId;
	private String commentdescription;
	/**
	 * @return the commentId
	 */
	public Integer getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the commentdescription
	 */
	public String getCommentdescription() {
		return commentdescription;
	}
	/**
	 * @param commentdescription the commentdescription to set
	 */
	public void setCommentdescription(String commentdescription) {
		this.commentdescription = commentdescription;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result + ((commentdescription == null) ? 0 : commentdescription.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + questionsId;
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
		QuestionariesCommentsDTO other = (QuestionariesCommentsDTO) obj;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		if (commentdescription == null) {
			if (other.commentdescription != null)
				return false;
		} else if (!commentdescription.equals(other.commentdescription))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (questionsId != other.questionsId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionCommentsDTO [commentId=" + commentId + ", questionsId=" + questionsId + ", createdDate="
				+ createdDate + ", emailId=" + emailId + ", commentdescription=" + commentdescription + "]";
	}
	
	
}
