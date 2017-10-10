package com.nisum.portal.service.dto;

import java.sql.Timestamp;
import java.util.List;

public class QuestionRepliesDTO {

	private int replyId;
	private String replyDescription;
	private Timestamp updatedDate;
	private String emailId;
	private String displayImage;
	
	private List<QuestionReplyCommentsDTO>  replyComments;
	
	
	
	/**
	 * @return the replyComments
	 */
	public List<QuestionReplyCommentsDTO> getReplyComments() {
		return replyComments;
	}
	/**
	 * @param replyComments the replyComments to set
	 */
	public void setReplyComments(List<QuestionReplyCommentsDTO> replyComments) {
		this.replyComments = replyComments;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionRepliesDTO [replyId=" + replyId + ", replyDescription=" + replyDescription + ", updatedDate="
				+ updatedDate + ", emailId=" + emailId + ", displayImage=" + displayImage + ", replyComments="
				+ replyComments + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayImage == null) ? 0 : displayImage.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((replyComments == null) ? 0 : replyComments.hashCode());
		result = prime * result + ((replyDescription == null) ? 0 : replyDescription.hashCode());
		result = prime * result + replyId;
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
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
		QuestionRepliesDTO other = (QuestionRepliesDTO) obj;
		if (displayImage == null) {
			if (other.displayImage != null)
				return false;
		} else if (!displayImage.equals(other.displayImage))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (replyComments == null) {
			if (other.replyComments != null)
				return false;
		} else if (!replyComments.equals(other.replyComments))
			return false;
		if (replyDescription == null) {
			if (other.replyDescription != null)
				return false;
		} else if (!replyDescription.equals(other.replyDescription))
			return false;
		if (replyId != other.replyId)
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}
	
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
