package com.nisum.portal.service.dto;

import java.sql.Timestamp;

public class QuestionReplyCommentsDTO {
	
	private Integer commentId;
	private int id;
	private Timestamp createdDate;
	private String emailId;
	private String commentDescription;
	/**
	 * @return the repliesId
	 */
	public int getid() {
		return id;
	}
	/**
	 * @param replyId the replyId to set
	 */
	public void setid(Integer id) {
		this.id = id;
	}
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
	 * @return the commentDescription
	 */
	public String getcommentDescription() {
		return commentDescription;
	}
	/**
	 * @param commentDescription the commentDescription to set
	 */
	public void setcommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result + ((commentDescription == null) ? 0 : commentDescription.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + id;
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
		QuestionReplyCommentsDTO other = (QuestionReplyCommentsDTO) obj;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		if (commentDescription == null) {
			if (other.commentDescription != null)
				return false;
		} else if (!commentDescription.equals(other.commentDescription))
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
		if (id != other.id)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReplyCommentsDTO [commentId=" + commentId + ", id=" + id + ", createdDate=" + createdDate
				+ ", emailId=" + emailId + ", commentDescription=" + commentDescription + "]";
	}

	
}
