package com.nisum.portal.data.dao.api;

import java.sql.Timestamp;
import java.util.List;
import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.MeetingRoom;

public interface BookMeetingRoomDAO {
	
	public  List<BookMeetingRoom> getUserBooking(String emailId);
	
	public BookMeetingRoom bookMeetingRoom(BookMeetingRoom bookMeetingRoom);
	
	public List<MeetingRoom> getAvailableMeetingRoom(int locationId, Timestamp beginTime, Timestamp endTime);
	
	public BookMeetingRoom save(BookMeetingRoom bookMeetingRoom);
	
	public BookMeetingRoom findByBookMeetingRoomId(int bookMeetingRoomId);
	

}
