package com.nisum.portal.data.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TrainingRequest")
public class TrainingRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TrainingRequestId")
	private Integer trainingRequestId;
	private String emailid;
	private String requestTrainingTitle;
	private String description;
	private Timestamp requestedDate;
	public Integer getTrainingRequestId() {
		return trainingRequestId;
	}
	public void setTrainingRequestId(Integer trainingRequestId) {
		this.trainingRequestId = trainingRequestId;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getRequestTrainingTitle() {
		return requestTrainingTitle;
	}
	public void setRequestTrainingTitle(String requestTrainingTitle) {
		this.requestTrainingTitle = requestTrainingTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Timestamp requestedDate) {
		this.requestedDate = requestedDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		result = prime * result + ((requestTrainingTitle == null) ? 0 : requestTrainingTitle.hashCode());
		result = prime * result + ((requestedDate == null) ? 0 : requestedDate.hashCode());
		result = prime * result + ((trainingRequestId == null) ? 0 : trainingRequestId.hashCode());
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
		TrainingRequest other = (TrainingRequest) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		if (requestTrainingTitle == null) {
			if (other.requestTrainingTitle != null)
				return false;
		} else if (!requestTrainingTitle.equals(other.requestTrainingTitle))
			return false;
		if (requestedDate == null) {
			if (other.requestedDate != null)
				return false;
		} else if (!requestedDate.equals(other.requestedDate))
			return false;
		if (trainingRequestId == null) {
			if (other.trainingRequestId != null)
				return false;
		} else if (!trainingRequestId.equals(other.trainingRequestId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TrainingRequest [trainingRequestId=" + trainingRequestId + ", emailid=" + emailid
				+ ", requestTrainingTitle=" + requestTrainingTitle + ", description=" + description + ", requestedDate="
				+ requestedDate + "]";
	}
}
