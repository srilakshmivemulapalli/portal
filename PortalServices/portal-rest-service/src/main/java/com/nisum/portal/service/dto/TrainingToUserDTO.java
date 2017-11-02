package com.nisum.portal.service.dto;

import java.io.Serializable;

public class TrainingToUserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer trainingToUserId;
	private Integer trainingId;
	private Integer trainingPresence;
	private String emailId;
	public Integer getTrainingToUserId() {
		return trainingToUserId;
	}
	public void setTrainingToUserId(Integer trainingToUserId) {
		this.trainingToUserId = trainingToUserId;
	}
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
	public Integer getTrainingPresence() {
		return trainingPresence;
	}
	public void setTrainingPresence(Integer trainingPresence) {
		this.trainingPresence = trainingPresence;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Override
	public String toString() {
		return "TrainingToUserDTO [trainingToUserId=" + trainingToUserId + ", trainingId=" + trainingId + ",trainingPresence="+trainingPresence+",emailId="+emailId+"]";
	}

}
