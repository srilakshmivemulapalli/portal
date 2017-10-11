package com.nisum.portal.service.dto;

import java.sql.Timestamp;

public class TrainingsDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer trainingId;
	private String description;
	private Timestamp trainingDate;
	private String trainerName;
	private String trainingTitle;
	private String trainingStatus;
	private String trainingType;
	private Integer trainingPresence;
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
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
	
	public Integer getTrainingPresence() {
		return trainingPresence;
	}
	public void setTrainingPresence(Integer trainingPresence) {
		this.trainingPresence = trainingPresence;
	}
	@Override
	public String toString() {
		return "CategoriesDTO [trainingId=" + trainingId + ", description=" + description + ", trainingDate="
				+ trainingDate + ", trainerName=" + trainerName +  ", trainingTitle="
						+ trainingTitle+ " , trainingStatus=" +trainingStatus+ ",trainingType="+ trainingType+",trainingPresence="+trainingPresence+" ]";		
	}
	
	
}
