/**
 * 
 */
package com.nisum.portal.service.dto;

import java.sql.Timestamp;

/**
 * 
 * @author nisum
 *
 */




public class MeetingRoomDTO {
	
	
	private String description;
    private int meetingRoomId;
    private int locationId;
    private Timestamp beginTime;
	private Timestamp endTime;
	private Timestamp startDate;
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the meetingRoomId
	 */
	public int getMeetingRoomId() {
		return meetingRoomId;
	}
	/**
	 * @param meetingRoomId the meetingRoomId to set
	 */
	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}
	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return the beginTime
	 */
	public Timestamp getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the startedDate
	 */
	public Timestamp getStartDate() {
		return startDate;
	}
	/**
	 * @param startedDate the startedDate to set
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + locationId;
		result = prime * result + meetingRoomId;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingRoomDTO other = (MeetingRoomDTO) obj;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (locationId != other.locationId)
			return false;
		if (meetingRoomId != other.meetingRoomId)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeetingRoomDTO [description=" + description + ", meetingRoomId=" + meetingRoomId + ", locationId="
				+ locationId + ", beginTime=" + beginTime + ", endTime=" + endTime + ", startDate=" + startDate
				+ "]";
	}
	
	
	
	
	
}
