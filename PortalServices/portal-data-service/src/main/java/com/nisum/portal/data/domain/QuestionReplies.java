package com.nisum.portal.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionReplies")
public class QuestionReplies implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column (name = "replyId")
	private int replyId;
	private String replyDescription;
	private Timestamp updatedDate;
	
	//@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "questId")
	private int questId;
	
	private String emailid;

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

	


	/**
	 * @return the emailid
	 */
	public String getEmailid() {
		return emailid;
	}

	/**
	 * @param emailid the emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	/**
	 * @return the questId
	 */
	public int getQuestId() {
		return questId;
	}

	/**
	 * @param questId the questId to set
	 */
	public void setQuestId(int questId) {
		this.questId = questId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + questId;
		result = prime * result + ((replyDescription == null) ? 0 : replyDescription.hashCode());
		result = prime * result + replyId;
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
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
		QuestionReplies other = (QuestionReplies) obj;
		if (questId != other.questId)
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
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuestionReplies [replyId=" + replyId + ", replyDescription=" + replyDescription + ", createdDate="
				 + updatedDate + ", questionId=" + questId + ", emailid=" + emailid
				+ "]";
	}
	
	
}
	

	