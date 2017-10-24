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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "trainingId", referencedColumnName = "trainingId")
	private Trainings trainings;
	private String feedback;
	private String emailId;
	private Timestamp createDate;
	private String feedbackStatus;
	public Integer getTrainingFeedBackId() {
		return trainingFeedBackId;
	}
	public void setTrainingFeedBackId(Integer trainingFeedBackId) {
		this.trainingFeedBackId = trainingFeedBackId;
	}
	public Trainings getTrainings() {
		return trainings;
	}
	public void setTrainings(Trainings trainings) {
		this.trainings = trainings;
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
	public String getFeedbackStatus() {
		return feedbackStatus;
	}
	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((feedbackStatus == null) ? 0 : feedbackStatus.hashCode());
		result = prime * result + ((trainingFeedBackId == null) ? 0 : trainingFeedBackId.hashCode());
		result = prime * result + ((trainings == null) ? 0 : trainings.hashCode());
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
		if (feedbackStatus == null) {
			if (other.feedbackStatus != null)
				return false;
		} else if (!feedbackStatus.equals(other.feedbackStatus))
			return false;
		if (trainingFeedBackId == null) {
			if (other.trainingFeedBackId != null)
				return false;
		} else if (!trainingFeedBackId.equals(other.trainingFeedBackId))
			return false;
		if (trainings == null) {
			if (other.trainings != null)
				return false;
		} else if (!trainings.equals(other.trainings))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TrainingFeedBack [trainingFeedBackId=" + trainingFeedBackId + ", trainings=" + trainings + ", feedback="
				+ feedback + ", emailId=" + emailId + ", createDate=" + createDate + ", feedbackStatus="
				+ feedbackStatus + "]";
	}

}
