package com.nisum.portal.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TrainingToUser")
public class TrainingToUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "trainingToUserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainingToUser other = (TrainingToUser) obj;
		if (trainingToUserId == null) {
			if (other.trainingToUserId != null)
				return false;
		} else if (!trainingToUserId.equals(other.trainingToUserId))
			return false;
		if (trainingId == null) {
			if (other.trainingId != null)
				return false;
		} else if (!trainingId.equals(other.trainingId))
			return false;
		if (trainingPresence == null) {
			if (other.trainingPresence != null)
				return false;
		} else if (!trainingPresence.equals(other.trainingPresence))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
	
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trainingToUserId == null) ? 0 : trainingToUserId.hashCode());
		result = prime * result + ((trainingId == null) ? 0 : trainingId.hashCode());
		result = prime * result + ((trainingPresence == null) ? 0 : trainingPresence.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "TrainingToUser [trainingToUserId=" + trainingToUserId + ", trainingId=" + trainingId + ",trainingPresence="+trainingPresence+",emailId="+emailId+"]";		
	}
	

}
