/**
 * 
 */
package com.nisum.portal.data.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author nisum
 *
 */

@Entity
@Table(name = "BookMeetingRoom")
public class BookMeetingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookMeetingRoomId;
	private String meetingTitle;
	private String emailId;
	@OneToOne
	@JoinColumn(name = "meetingRoomId", referencedColumnName = "meetingRoomId")
	private MeetingRoom meetingRoom;

	private Timestamp beginTime;
	private Timestamp endTime;

	private int headCount;

	@OneToOne
	@JoinColumn(name = "locationId", referencedColumnName = "locationId")
	private Location location;

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	public int getBookMeetingRoomId() {
		return bookMeetingRoomId;
	}

	public void setBookMeetingRoomId(int bookMeetingRoomId) {
		this.bookMeetingRoomId = bookMeetingRoomId;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	/**
	 * @return the user
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the meetingRoom
	 */
	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	/**
	 * @param meetingRoom
	 *            the meetingRoom to set
	 */

	public Timestamp getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
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
		result = prime * result + ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + bookMeetingRoomId;

		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + headCount;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((meetingRoom == null) ? 0 : meetingRoom.hashCode());
		result = prime * result + ((meetingTitle == null) ? 0 : meetingTitle.hashCode());
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
		BookMeetingRoom other = (BookMeetingRoom) obj;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (bookMeetingRoomId != other.bookMeetingRoomId)
			return false;

		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (headCount != other.headCount)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (meetingRoom == null) {
			if (other.meetingRoom != null)
				return false;
		} else if (!meetingRoom.equals(other.meetingRoom))
			return false;
		if (meetingTitle == null) {
			if (other.meetingTitle != null)
				return false;
		} else if (!meetingTitle.equals(other.meetingTitle))
			return false;
		return true;
	}

	public int getHeadCount() {
		return headCount;
	}

	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookMeetingRoom [bookMeetingRoomId=" + bookMeetingRoomId + ", meetingTitle=" + meetingTitle
				+ ", emailId=" + emailId + ", meetingRoom=" + meetingRoom + ", beginTime=" + beginTime + ", endTime="
				+ endTime + ", headCount=" + headCount + ", location=" + location + "]";
	}

}
