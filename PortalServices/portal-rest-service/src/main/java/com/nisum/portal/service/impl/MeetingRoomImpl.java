package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nisum.portal.data.dao.api.MeetingRoomDAO;
import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.service.api.MeetingRoomService;
import com.nisum.portal.service.dto.BookMeetingRoomDTO;
import com.nisum.portal.service.dto.MeetingRoomDTO;
import com.nisum.portal.util.BookMeetingRoomUtil;
import com.nisum.portal.util.MeetingRoomUtil;

@Service
public class MeetingRoomImpl implements MeetingRoomService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MeetingRoomDAO meetingRoomDAO;

	
	public String registerMeetingRoom(MeetingRoomDTO meetingRoomDTO){
		logger.info("In registerMeetingRoom()..MeetingRoomImpl...");
		
		MeetingRoom meetingRoom =MeetingRoomUtil.convertDtoObjectToDao(meetingRoomDTO);
		
		MeetingRoom tempMeetingRoom = meetingRoomDAO.findByMeetingRoomId(meetingRoom.getMeetingRoomId());
			if(tempMeetingRoom == null){
				 meetingRoomDAO.save(meetingRoom);	
				 return "Saved Successfully...";
			}else{
				
				tempMeetingRoom.setMeetingRoomName(meetingRoom.getMeetingRoomName());
				 meetingRoomDAO.save(tempMeetingRoom);
				return  "Updated Successfully...";
			}
	}
	
	public List<BookMeetingRoomDTO> getAllMeetingRoom(int locationId,Timestamp startDate){
		logger.info("In MeetingRoomImpl....getAllMeetingRoom()....");
		List<BookMeetingRoom> meetingRoomList = meetingRoomDAO.findAllByLocationIdAndDate(locationId,startDate);
		return BookMeetingRoomUtil.convertDaoListToDto(meetingRoomList);
	}

	

}
