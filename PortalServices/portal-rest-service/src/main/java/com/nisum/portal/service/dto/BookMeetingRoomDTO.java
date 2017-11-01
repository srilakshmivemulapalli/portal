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
 


public class BookMeetingRoomDTO {
	
	
	private int bookMeetingRoomId;
	private String meetingTitle;
	private int meetingRoomId;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String emailId;
	private int locationId;
	private Timestamp createdDate;
	private Timestamp bookingDate;
	
	
	
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginTime == null) ? 0 : beginTime.hashCode());
		result = prime * result + bookMeetingRoomId;
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + headCount;
		result = prime * result + locationId;
		result = prime * result + meetingRoomId;
		result = prime * result + ((meetingTitle == null) ? 0 : meetingTitle.hashCode());
		result = prime * result + ((updatedTime == null) ? 0 : updatedTime.hashCode());
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
		BookMeetingRoomDTO other = (BookMeetingRoomDTO) obj;
		if (beginTime == null) {
			if (other.beginTime != null)
				return false;
		} else if (!beginTime.equals(other.beginTime))
			return false;
		if (bookMeetingRoomId != other.bookMeetingRoomId)
			return false;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
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
		if (locationId != other.locationId)
			return false;
		if (meetingRoomId != other.meetingRoomId)
			return false;
		if (meetingTitle == null) {
			if (other.meetingTitle != null)
				return false;
		} else if (!meetingTitle.equals(other.meetingTitle))
			return false;
		if (updatedTime == null) {
			if (other.updatedTime != null)
				return false;
		} else if (!updatedTime.equals(other.updatedTime))
			return false;
		return true;
	}
	
	private int headCount;
	private Timestamp createdTime;
	private Timestamp updatedTime;
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
	
	
	
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the bookingDate
	 */
	public Timestamp getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookMeetingRoomDTO [bookMeetingRoomId=" + bookMeetingRoomId + ", meetingTitle=" + meetingTitle
				+ ", meetingRoomId=" + meetingRoomId + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", emailId=" + emailId + ", locationId=" + locationId + ", createdDate=" + createdDate
				+ ", bookingDate=" + bookingDate + ", headCount=" + headCount + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
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
	
}
