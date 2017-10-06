package com.nisum.portal.service.dto;

import java.sql.Timestamp;

public class TrainingRequestDTO {
	private Integer trainingRequestId;
	private Integer userId;
	private Integer courseId;
	private String description;
	private Timestamp requestedDate;
	public Integer getTrainingRequestId() {
		return trainingRequestId;
	}
	public void setTrainingRequestId(Integer trainingRequestId) {
		this.trainingRequestId = trainingRequestId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((requestedDate == null) ? 0 : requestedDate.hashCode());
		result = prime * result + ((trainingRequestId == null) ? 0 : trainingRequestId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TrainingRequestDTO [trainingRequestId=" + trainingRequestId + ", userId=" + userId + ", courseId="
				+ courseId + ", description=" + description + ", requestedDate=" + requestedDate + "]";
	}
}
