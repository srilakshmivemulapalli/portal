package com.nisum.portal.service.dto;

import java.sql.Timestamp;

public class TrainingRequestDTO {
	private Integer trainingRequestId;
	private String emailId;
	private String requestTrainingTitle;
	private String description;
	private Timestamp requestedDate;
	public Integer getTrainingRequestId() {
		return trainingRequestId;
	}
	public void setTrainingRequestId(Integer trainingRequestId) {
		this.trainingRequestId = trainingRequestId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
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
		TrainingRequestDTO other = (TrainingRequestDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
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
		return "TrainingRequestDTO [trainingRequestId=" + trainingRequestId + ", emailid=" + emailId
				+ ", requestTrainingTitle=" + requestTrainingTitle + ", description=" + description + ", requestedDate="
				+ requestedDate + "]";
	}
}
