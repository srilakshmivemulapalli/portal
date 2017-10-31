package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.MeetingRoom;

public interface MeetingRoomDAO {
	public MeetingRoom findByMeetingRoomId(int meetingRoomId);
	
	 MeetingRoom save( MeetingRoom meetingRoom);
	 
	public List<MeetingRoom> findAllByLocationId(int locationId);

}
