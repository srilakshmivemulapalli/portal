package com.nisum.portal.service.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class TrainingsDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer trainingId;
	private String description;
	private Timestamp trainingStartTime;
	private String trainerEmailId;
	private String trainingTitle;
	private String trainingType;
	private Integer trainingPresence;
	private Integer noOfStudents;
	private Timestamp trainingStartDate;
	private Timestamp trainingEndDate;
	private Timestamp trainingEndTime;
	private Long duration;
	private String trainerName;
	private String displayImage;
	private Integer noOfComments;
	private Integer trainingToUserId;
	private ArrayList<LinkedHashMap<String,String>> commentDescriptions;
	private Integer commentStatus;
	private Integer trainingStatus;
	private String trainingRemarks;
	public Integer getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
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
	
	public Integer getTrainingPresence() {
		return trainingPresence;
	}
	public void setTrainingPresence(Integer trainingPresence) {
		this.trainingPresence = trainingPresence;
	}
	public Integer getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(Integer noOfStudents) {
		this.noOfStudents = noOfStudents;
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
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getDisplayImage() {
		return displayImage;
	}
	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}
	public Integer getNoOfComments() {
		return noOfComments;
	}
	public void setNoOfComments(Integer noOfComments) {
		this.noOfComments = noOfComments;
	}
	public Integer getTrainingToUserId() {
		return trainingToUserId;
	}
	public void setTrainingToUserId(Integer trainingToUserId) {
		this.trainingToUserId = trainingToUserId;
	}
	public ArrayList<LinkedHashMap<String, String>> getCommentDescriptions() {
		return commentDescriptions;
	}
	public void setCommentDescriptions(ArrayList<LinkedHashMap<String, String>> commentDescriptions) {
		this.commentDescriptions = commentDescriptions;
	}
	public Integer getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}
	public Integer getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(Integer trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	public String getTrainingRemarks() {
		return trainingRemarks;
	}
	public void setTrainingRemarks(String trainingRemarks) {
		this.trainingRemarks = trainingRemarks;
	}
	@Override
	public String toString() {
		return "CategoriesDTO [trainingId=" + trainingId + ", description=" + description + ", trainingStartTime="
				+ trainingStartTime + ", trainerEmailId=" + trainerEmailId +  ", trainingTitle="
				+ trainingTitle+ ",trainingType="
				+ trainingType+",trainingPresence="+trainingPresence+",noOfStudents="
				+noOfStudents+",trainingStartDate="+trainingStartDate+",trainingEndDate="
				+trainingEndDate+" ,trainingEndTime="+trainingEndTime+"+duration="+duration+
						",trainerName="+trainerName+",displayImage="+displayImage+",noOfComments="
				+noOfComments+",trainingToUserId="+trainingToUserId+"+commentDescriptions="+commentDescriptions+"commentStatus="
				+commentStatus+",trainingStatus="+trainingStatus+",trainingRemarks="+trainingRemarks+"]";	
	}

	
	
}
