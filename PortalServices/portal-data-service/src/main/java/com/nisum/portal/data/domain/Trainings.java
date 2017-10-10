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
	private Timestamp trainingDate;
	private String trainerName;
	private String trainingTitle;
	private String trainingStatus;
	private String trainingType;
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
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getTrainingTitle() {
		return trainingTitle;
	}
	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}
	public Timestamp getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(Timestamp trainingDate) {
		this.trainingDate = trainingDate;
	}
	public String getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
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
		if (trainingDate == null) {
			if (other.trainingDate != null)
				return false;
		} else if (!trainingDate.equals(other.trainingDate))
			return false;
		if (trainerName == null) {
			if (other.trainerName != null)
				return false;
		} else if (!trainerName.equals(other.trainerName))
			return false;
		if (trainingStatus == null) {
			if (other.trainingStatus != null)
				return false;
		} else if (!trainingStatus.equals(other.trainingStatus))
			return false;
		if (trainingType == null) {
			if (other.trainingType != null)
				return false;
		} else if (!trainingType.equals(other.trainingType))
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
		result = prime * result + ((trainingDate == null) ? 0 : trainingDate.hashCode());
		result = prime * result + ((trainerName == null) ? 0 : trainerName.hashCode());
		result = prime * result + ((trainingTitle == null) ? 0 : trainingTitle.hashCode());
		result = prime * result + ((trainingStatus == null) ? 0 : trainingStatus.hashCode());
		result = prime * result + ((trainingType == null) ? 0 : trainingType.hashCode());
		
		return result;
	}
	
	@Override
	public String toString() {
		return "Trainings [trainingId=" + trainingId + ", description=" + description + ", trainingDate="
				+ trainingDate + ", trainerName=" + trainerName +  ", trainingTitle="
						+ trainingTitle+ " , trainingStatus=" +trainingStatus+ ",trainingType="+ trainingType+" ]";		
	}
	
	
}
