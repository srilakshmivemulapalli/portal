package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.service.dto.MeetingRoomDTO;

public class MeetingRoomUtil {
	public static List<MeetingRoomDTO> convertDaoListToDto(List<MeetingRoom> meetingRoom) {
		List<MeetingRoomDTO> meetingRoomDTOList = new ArrayList<MeetingRoomDTO>();
		
		if (CollectionUtils.isNotEmpty(meetingRoom)) {
			for(MeetingRoom meetingRoomDAO : meetingRoom){
				MeetingRoomDTO meetingRoomDTO = new MeetingRoomDTO();
				meetingRoomDTO.setDescription(meetingRoomDAO.getDescription());
				meetingRoomDTO.setLocationId(meetingRoomDAO.getLocation());
				meetingRoomDTO.setMeetingRoomId(meetingRoomDAO.getMeetingRoomId());
				meetingRoomDTO.setName(meetingRoomDAO.getName());
				meetingRoomDTOList.add(meetingRoomDTO);
				
			}
		}
		return meetingRoomDTOList;
	}
	
	public static MeetingRoomDTO convertDaoObjectToDto(MeetingRoom meetingRoom) 
	{
		MeetingRoomDTO meetingRoomDTO = new MeetingRoomDTO();
		meetingRoomDTO.setDescription(meetingRoom.getDescription());
		meetingRoomDTO.setLocationId(meetingRoom.getLocation());
		meetingRoomDTO.setMeetingRoomId(meetingRoom.getMeetingRoomId());
		meetingRoomDTO.setName(meetingRoom.getName());
		
		
		return meetingRoomDTO;
		
		


	}
	
public static MeetingRoom convertDtoObjectToDao(MeetingRoomDTO meetingRoomDTO) 
{
		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setDescription(meetingRoomDTO.getDescription());
		meetingRoom.setLocation(meetingRoomDTO.getLocationId());
	meetingRoom.setMeetingRoomId(meetingRoomDTO.getMeetingRoomId());
		meetingRoom.setName(meetingRoomDTO.getName());
		
		
		return meetingRoom;
		
		


	}

}
