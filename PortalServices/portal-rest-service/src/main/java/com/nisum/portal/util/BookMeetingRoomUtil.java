package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.service.dto.BookMeetingRoomDTO;


public class BookMeetingRoomUtil {
	
	public static List<BookMeetingRoomDTO> convertDaoListToDto(List<BookMeetingRoom> bookMeetingRoom) {
		List<BookMeetingRoomDTO> bookMeetingRoomDTOList = new ArrayList<BookMeetingRoomDTO>();
		
		if (CollectionUtils.isNotEmpty(bookMeetingRoom)) {
			for(BookMeetingRoom bookMeetingRoomDAO : bookMeetingRoom){
				BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();
				bookMeetingRoomDTO.setBeginTime(bookMeetingRoomDAO.getBeginTime());
				bookMeetingRoomDTO.setBookMeetingRoomId(bookMeetingRoomDAO.getBookMeetingRoomId());
				bookMeetingRoomDTO.setCreatedTime(bookMeetingRoomDAO.getCreatedTime());
				bookMeetingRoomDTO.setEndTime(bookMeetingRoomDAO.getEndTime());
				bookMeetingRoomDTO.setHeadCount(bookMeetingRoomDAO.getHeadCount());
				//bookMeetingRoomDTO.setMeetingRoomDTO(bookMeetingRoomDAO.getMeetingRoom());

				bookMeetingRoomDTO.setMeetingTitle(bookMeetingRoomDAO.getMeetingTitle());
				bookMeetingRoomDTO.setUpdatedTime(bookMeetingRoomDAO.getUpdatedTime());
				bookMeetingRoomDTO.setEmailId(bookMeetingRoomDAO.getEmailId());
				bookMeetingRoomDTO.setLocationId(bookMeetingRoomDAO.getLocation().getLocationId());
				bookMeetingRoomDTO.setMeetingRoomId(bookMeetingRoomDAO.getMeetingRoom().getMeetingRoomId());
				
				bookMeetingRoomDTOList.add(bookMeetingRoomDTO);
				
			}
		}
		return bookMeetingRoomDTOList;
	}
	
	public static BookMeetingRoomDTO convertDaoObjectToDto(BookMeetingRoom bookMeetingRoom) 
	{
		BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();
		bookMeetingRoomDTO.setBeginTime(bookMeetingRoom.getBeginTime());
		bookMeetingRoomDTO.setBookMeetingRoomId(bookMeetingRoom.getBookMeetingRoomId());
		bookMeetingRoomDTO.setCreatedTime(bookMeetingRoom.getCreatedTime());
		bookMeetingRoomDTO.setEndTime(bookMeetingRoom.getEndTime());
		bookMeetingRoomDTO.setHeadCount(bookMeetingRoom.getHeadCount());
		bookMeetingRoomDTO.setMeetingTitle(bookMeetingRoom.getMeetingTitle());
		bookMeetingRoomDTO.setUpdatedTime(bookMeetingRoom.getUpdatedTime());
		bookMeetingRoomDTO.setEmailId(bookMeetingRoom.getEmailId());
		bookMeetingRoomDTO.setLocationId(bookMeetingRoom.getLocation().getLocationId());
		
//		MeetingRoomDTO meetingRoomDTO=new MeetingRoomDTO();
//		meetingRoomDTO.setDescription(bookMeetingRoom.getMeetingRoom().getDescription());
//		meetingRoomDTO.setLocationId(bookMeetingRoom.getLocation().getLocationId());
//		meetingRoomDTO.setMeetingRoomId(bookMeetingRoom.getMeetingRoom().getMeetingRoomId());
//		meetingRoomDTO.setName(bookMeetingRoom.getMeetingRoom().getName());
		
		bookMeetingRoomDTO.setMeetingRoomId(bookMeetingRoom.getMeetingRoom().getMeetingRoomId());
		return bookMeetingRoomDTO;
		
		


	}
	
	public static BookMeetingRoom convertDtoObjectToDao(BookMeetingRoomDTO bookMeetingRoomDTO) 
	{
		BookMeetingRoom bookMeetingRoom = new BookMeetingRoom();
		bookMeetingRoom.setBeginTime(bookMeetingRoomDTO.getBeginTime());
		bookMeetingRoom.setBookMeetingRoomId(bookMeetingRoomDTO.getBookMeetingRoomId());
		bookMeetingRoom.setCreatedTime(bookMeetingRoomDTO.getCreatedTime());
		bookMeetingRoom.setEndTime(bookMeetingRoomDTO.getEndTime());
		bookMeetingRoom.setHeadCount(bookMeetingRoomDTO.getHeadCount());
		bookMeetingRoom.setMeetingTitle(bookMeetingRoomDTO.getMeetingTitle());
		bookMeetingRoom.setUpdatedTime(bookMeetingRoomDTO.getUpdatedTime());
		bookMeetingRoom.setEmailId(bookMeetingRoomDTO.getEmailId());
		bookMeetingRoom.setLocation(new Location(bookMeetingRoomDTO.getLocationId()));
		bookMeetingRoom.setMeetingRoom(new MeetingRoom(bookMeetingRoomDTO.getMeetingRoomId()));
		
		return bookMeetingRoom;
		
		


	}
	

}
