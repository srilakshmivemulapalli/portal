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
	
	
	private String meetingRoomName;
    private int meetingRoomId;
    private int locationId;
    
	
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
	 * @return the meetingRoomName
	 */
	public String getMeetingRoomName() {
		return meetingRoomName;
	}
	/**
	 * @param meetingRoomName the meetingRoomName to set
	 */
	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + locationId;
		result = prime * result + meetingRoomId;
		result = prime * result + ((meetingRoomName == null) ? 0 : meetingRoomName.hashCode());
		
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
		
		if (locationId != other.locationId)
			return false;
		if (meetingRoomId != other.meetingRoomId)
			return false;
		if (meetingRoomName == null) {
			if (other.meetingRoomName != null)
				return false;
		} else if (!meetingRoomName.equals(other.meetingRoomName))
			return false;
		
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeetingRoomDTO [meetingRoomName=" + meetingRoomName + ", meetingRoomId=" + meetingRoomId
				+ ", locationId=" + locationId + "]";
	}
	
	
	
	
	
}
