package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.service.dto.MeetingRoomDTO;

public interface MeetingRoomService {
	String registerMeetingRoom(MeetingRoomDTO meetingRoomDTO);
	
	List<MeetingRoomDTO> getAllMeetingRoom(int locationId);

}