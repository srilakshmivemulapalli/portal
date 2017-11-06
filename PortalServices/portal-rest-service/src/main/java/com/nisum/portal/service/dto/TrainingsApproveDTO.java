package com.nisum.portal.service.dto;

import java.util.List;

public class TrainingsApproveDTO {
	private static final long serialVersionUID = 1L;
	
	private Integer noOfClassTrainings;
	private Integer noOfOnlineTrainings;
	private Integer noOfClassPendings;
	private Integer noOfOnlinePendings;
	private Integer noOfClassApprovals;
	private Integer noOfOnlineApprovals;
	private List<TrainingsDTO> trainings;
	private Integer noOfClassRejected;
	private Integer noOfOnlineRejected;
	public Integer getNoOfClassTrainings() {
		return noOfClassTrainings;
	}
	public void setNoOfClassTrainings(Integer noOfClassTrainings) {
		this.noOfClassTrainings = noOfClassTrainings;
	}
	public Integer getNoOfOnlineTrainings() {
		return noOfOnlineTrainings;
	}
	public void setNoOfOnlineTrainings(Integer noOfOnlineTrainings) {
		this.noOfOnlineTrainings = noOfOnlineTrainings;
	}
	public Integer getNoOfClassPendings() {
		return noOfClassPendings;
	}
	public void setNoOfClassPendings(Integer noOfClassPendings) {
		this.noOfClassPendings = noOfClassPendings;
	}
	public Integer getNoOfOnlinePendings() {
		return noOfOnlinePendings;
	}
	public void setNoOfOnlinePendings(Integer noOfOnlinePendings) {
		this.noOfOnlinePendings = noOfOnlinePendings;
	}
	public Integer getNoOfClassApprovals() {
		return noOfClassApprovals;
	}
	public void setNoOfClassApprovals(Integer noOfClassApprovals) {
		this.noOfClassApprovals = noOfClassApprovals;
	}
	public Integer getNoOfOnlineApprovals() {
		return noOfOnlineApprovals;
	}
	public void setNoOfOnlineApprovals(Integer noOfOnlineApprovals) {
		this.noOfOnlineApprovals = noOfOnlineApprovals;
	}
	public List<TrainingsDTO> getTrainings() {
		return trainings;
	}
	public void setTrainings(List<TrainingsDTO> trainings) {
		this.trainings = trainings;
	}
	public Integer getNoOfClassRejected() {
		return noOfClassRejected;
	}
	public void setNoOfClassRejected(Integer noOfClassRejected) {
		this.noOfClassRejected = noOfClassRejected;
	}
	public Integer getNoOfOnlineRejected() {
		return noOfOnlineRejected;
	}
	public void setNoOfOnlineRejected(Integer noOfOnlineRejected) {
		this.noOfOnlineRejected = noOfOnlineRejected;
	}
	@Override
	public String toString() {
		
		return "TrainingsApproveDTO [noOfClassTrainings="+noOfClassTrainings+", noOfOnlineTrainings="
		+noOfOnlineTrainings+",noOfClassPendings=+noOfClassPendings+,noOfOnlinePendings="+noOfOnlinePendings
		+",noOfClassApprovals="+noOfClassApprovals+",noOfOnlineApprovals="+noOfOnlineApprovals+",noOfClassRejected="+
		noOfClassRejected+",noOfOnlineRejected="+noOfOnlineRejected+",trainings="+trainings+"]";
	}


}
