package com.nisum.portal.service.dto;

import java.sql.Timestamp;


public class TrainingFeedBackDTO {
	private Integer trainingFeedBackId;
	private Integer trainingId;
	private String feedback;
	private String rating;
	private Timestamp createDate;
	public Integer getTrainingFeedBackId() {
		return trainingFeedBackId;
	}
	public void setTrainingFeedBackId(Integer trainingFeedBackId) {
		this.trainingFeedBackId = trainingFeedBackId;
	}
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
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
		TrainingFeedBackDTO other = (TrainingFeedBackDTO) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
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
		return "TrainingFeedBackDTO [trainingFeedBackId=" + trainingFeedBackId + ", trainingId=" + trainingId
				+ ", feedback=" + feedback + ", rating=" + rating + ", createDate=" + createDate + "]";
	}

}
