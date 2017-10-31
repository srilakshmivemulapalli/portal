package com.nisum.portal.service.api;


import java.sql.Timestamp;
import java.util.List;

import com.nisum.portal.service.dto.BookMeetingRoomDTO;
import com.nisum.portal.service.dto.MeetingRoomDTO;


public interface BookMeetingRoomService {
	List<MeetingRoomDTO> getAvailableMeetingRoom(int locationId, Timestamp beginTime, Timestamp endTime);
	
	String bookMeetingRoom(BookMeetingRoomDTO bookMeetingRoomDTO);
	
	List<BookMeetingRoomDTO> getUserBooking(String emailId);

}
