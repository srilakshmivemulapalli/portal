package com.nisum.portal.service.dto;

import java.util.List;

public class TrainingsApproveDTO {
	private static final long serialVersionUID = 1L;
	
	private List<TrainingsDTO> trainings;
	private TrainingsDetails trainingsDetails;
	public List<TrainingsDTO> getTrainings() {
		return trainings;
	}
	public void setTrainings(List<TrainingsDTO> trainings) {
		this.trainings = trainings;
	}
	public TrainingsDetails getTrainingsDetails() {
		return trainingsDetails;
	}
	public void setTrainingsDetails(TrainingsDetails trainingsDetails) {
		this.trainingsDetails = trainingsDetails;
	}
	@Override
	public String toString() {
			
			return "TrainingsApproveDTO [trainings="+trainings+",trainingsDetails="+trainingsDetails+"]";
		}
	


}
