package com.nisum.portal.data.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trainings")
public class Trainings {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "trainingId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trainingId;
	private String description;
	private Timestamp trainingStartTime;
	private String trainerEmailId;
	private String trainingTitle;
	private String trainingType;
	private Timestamp trainingStartDate;
	private Timestamp trainingEndDate;
	private Timestamp trainingEndTime;
	private Integer trainingStatus;
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrainerEmailId() {
		return trainerEmailId;
	}
	public void setTrainerEmailId(String trainerEmailId) {
		this.trainerEmailId = trainerEmailId;
	}
	public String getTrainingTitle() {
		return trainingTitle;
	}
	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}
	public Timestamp getTrainingStartTime() {
		return trainingStartTime;
	}
	public void setTrainingStartTime(Timestamp trainingStartTime) {
		this.trainingStartTime = trainingStartTime;
	}
	public Timestamp getTrainingEndTime() {
		return trainingEndTime;
	}
	public void setTrainingEndTime(Timestamp trainingEndTime) {
		this.trainingEndTime = trainingEndTime;
	}
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	public Timestamp getTrainingStartDate() {
		return trainingStartDate;
	}
	public void setTrainingStartDate(Timestamp trainingStartDate) {
		this.trainingStartDate = trainingStartDate;
	}
	public Timestamp getTrainingEndDate() {
		return trainingEndDate;
	}
	public void setTrainingEndDate(Timestamp trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}
	public Integer getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(Integer trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainings other = (Trainings) obj;
		if (trainingId == null) {
			if (other.trainingId != null)
				return false;
		} else if (!trainingId.equals(other.trainingId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (trainingStartTime == null) {
			if (other.trainingStartTime != null)
				return false;
		} else if (!trainingStartTime.equals(other.trainingStartTime))
			return false;
		if (trainerEmailId == null) {
			if (other.trainerEmailId != null)
				return false;
		} else if (!trainerEmailId.equals(other.trainerEmailId))
			return false;
		if (trainingType == null) {
			if (other.trainingType != null)
				return false;
		} else if (!trainingType.equals(other.trainingType))
			return false;
		if (trainingStartDate == null) {
			if (other.trainingStartDate != null)
				return false;
		} else if (!trainingStartDate.equals(other.trainingStartDate))
			return false;
		if (trainingEndDate == null) {
			if (other.trainingEndDate != null)
				return false;
		} else if (!trainingEndDate.equals(other.trainingEndDate))
			return false;
		if (trainingEndTime == null) {
			if (other.trainingEndTime != null)
				return false;
		} else if (!trainingEndTime.equals(other.trainingEndTime))
			return false;
		if (trainingStatus == null) {
			if (other.trainingStatus != null)
				return false;
		} else if (!trainingStatus.equals(other.trainingStatus))
			return false;
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trainingId == null) ? 0 : trainingId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((trainingStartDate == null) ? 0 : trainingStartDate.hashCode());
		result = prime * result + ((trainingEndDate == null) ? 0 : trainingEndDate.hashCode());
		result = prime * result + ((trainerEmailId == null) ? 0 : trainerEmailId.hashCode());
		result = prime * result + ((trainingTitle == null) ? 0 : trainingTitle.hashCode());
		result = prime * result + ((trainingType == null) ? 0 : trainingType.hashCode());
		result = prime * result + ((trainingStartTime == null) ? 0 : trainingStartTime.hashCode());
		result = prime * result + ((trainingEndTime == null) ? 0 : trainingEndTime.hashCode());
		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
		
		return result;
	}
	
	@Override
	public String toString() {
		return "Trainings [trainingId=" + trainingId + ", description=" + description + ", trainingStartTime="
				+ trainingStartTime + ", trainerName=" + trainerEmailId +  ", trainingTitle="
						+ trainingTitle+ ",trainingType="
				+ trainingType+",trainingStartDate="+trainingStartDate+",trainingEndDate="+trainingEndDate+",trainingEndTime="+trainingEndTime
				+",trainingStatus="+trainingStatus+"]";		
	}
	public Trainings(Integer trainingId) {
		this.trainingId = trainingId;
	}
	public Trainings() {
		// TODO Auto-generated constructor stub
	}
	
}
