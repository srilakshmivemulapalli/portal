package com.nisum.portal.data.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TrainingFeedBack")
public class TrainingFeedBack implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trainingFeedBackId")
	private Integer trainingFeedBackId;
	private Integer trainingId;
	private String feedback;
	private String emailId;
	private Timestamp createDate;
	public Integer getTrainingFeedBackId() {
		return trainingFeedBackId;
	}
	public void setTrainingFeedBackId(Integer trainingFeedBackId) {
		this.trainingFeedBackId = trainingFeedBackId;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((trainingFeedBackId == null) ? 0 : trainingFeedBackId.hashCode());
		result = prime * result + ((trainingId == null) ? 0 : trainingId.hashCode());
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
		TrainingFeedBack other = (TrainingFeedBack) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (trainingFeedBackId == null) {
			if (other.trainingFeedBackId != null)
				return false;
		} else if (!trainingFeedBackId.equals(other.trainingFeedBackId))
			return false;
		if (trainingId == null) {
			if (other.trainingId != null)
				return false;
		} else if (!trainingId.equals(other.trainingId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TrainingFeedBack [trainingFeedBackId=" + trainingFeedBackId + ", trainingId=" + trainingId + ", feedback="
				+ feedback + ", emailId=" + emailId + ", createDate=" + createDate + "]";
	}

}
