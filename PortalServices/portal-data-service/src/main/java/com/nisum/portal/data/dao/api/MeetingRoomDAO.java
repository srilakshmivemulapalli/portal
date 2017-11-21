package com.nisum.portal.data.dao.api;

import java.sql.Timestamp;
import java.util.List;

import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.MeetingRoom;

public interface MeetingRoomDAO {
	public MeetingRoom findByMeetingRoomId(int meetingRoomId);
	
	 MeetingRoom save( MeetingRoom meetingRoom);
	 
	List<BookMeetingRoom> findAllByLocationIdAndDate(int locationId,Timestamp startDate);

}
