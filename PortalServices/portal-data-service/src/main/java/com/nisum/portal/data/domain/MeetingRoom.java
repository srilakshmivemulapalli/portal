/**
 * 
 */
package com.nisum.portal.data.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author nisum
 *
 */

@Entity
@Table(name = "MeetingRoom")
public class MeetingRoom {

	private String meetingRoomName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int meetingRoomId;
	private int location;

	public MeetingRoom(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	public MeetingRoom() {
	}

	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(int location) {
		this.location = location;
	}

	/**
	 * @return the meetingRoomName
	 */
	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	/**
	 * @param meetingRoomName
	 *            the meetingRoomName to set
	 */
	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
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

		result = prime * result + location;
		result = prime * result + meetingRoomId;
		result = prime * result + ((meetingRoomName == null) ? 0 : meetingRoomName.hashCode());

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		MeetingRoom other = (MeetingRoom) obj;

		if (location != other.location)
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

	public int getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeetingRoom [meetingRoomName=" + meetingRoomName + ", meetingRoomId=" + meetingRoomId + ", location="
				+ location + "]";
	}

}
