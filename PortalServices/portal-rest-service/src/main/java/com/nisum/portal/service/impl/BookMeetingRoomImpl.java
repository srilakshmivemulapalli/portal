package com.nisum.portal.service.impl;


import java.awt.print.Book;
import java.sql.Timestamp;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.BookMeetingRoomDAO;
import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.data.repository.BookMeetingRoomRepository;
import com.nisum.portal.data.repository.LocationRepository;
import com.nisum.portal.data.repository.MeetingRoomRepository;
import com.nisum.portal.service.api.BookMeetingRoomService;
import com.nisum.portal.service.dto.BookMeetingRoomDTO;
import com.nisum.portal.service.dto.MeetingRoomDTO;
import com.nisum.portal.util.BookMeetingRoomUtil;
import com.nisum.portal.util.MeetingRoomUtil;

@Service
public class BookMeetingRoomImpl implements BookMeetingRoomService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	BookMeetingRoomDAO bookMeetingRoomDAO;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	MeetingRoomRepository meetingRoomRepository;
	
	@Autowired
	BookMeetingRoomRepository bookMeetingRoomRepository;


	public String bookMeetingRoom(BookMeetingRoomDTO bookMeetingRoomDTO){
		logger.info("In bookMeetingRoom()..BookMeetingRoomImpl...");

		System.out.println(bookMeetingRoomDTO.getBeginTime()+" "+bookMeetingRoomDTO.getEndTime());
		int locationId = bookMeetingRoomDTO.getLocationId();
		Location location = locationRepository.findOne(locationId);
		
		String startTimeString = bookMeetingRoomDTO.getBeginTime().toString();
		String endTimeString = bookMeetingRoomDTO.getEndTime().toString();
		
		Timestamp startTimeStamp = Timestamp.valueOf(BookMeetingRoomUtil.getFormatedDateAndTime(startTimeString));
		Timestamp endTimeStamp = Timestamp.valueOf(BookMeetingRoomUtil.getFormatedDateAndTime(endTimeString));
		
		int meetingRoomId = bookMeetingRoomDTO.getMeetingRoomId();
		MeetingRoom meetingRoom = meetingRoomRepository.findOne(meetingRoomId);
		if (location != null && meetingRoom != null) {
			BookMeetingRoom bookedMeetingRoom = bookMeetingRoomRepository.getMeetingRoomForTimePeriod(startTimeStamp, endTimeStamp);
			if (bookedMeetingRoom == null) {
				BookMeetingRoom bookMeetingRoomDao = BookMeetingRoomUtil.convertDtoObjectToDao(bookMeetingRoomDTO);
				BookMeetingRoom bookMeetingRoom = bookMeetingRoomDAO.findByBookMeetingRoomId(bookMeetingRoomDao.getBookMeetingRoomId());
				if(bookMeetingRoomDTO.getBookMeetingRoomId() == 0){
					bookMeetingRoomDAO.save(bookMeetingRoomDao);	
					return "Saved Successfully...";
				}else {
					bookMeetingRoom = bookMeetingRoomDAO.save(bookMeetingRoom);
					return "updated Successfully...";
				} 
			} else {
				return "Meeting alredy booked by someone";
			}
		} else {
			return "Location or meeting Id not found";
		}

	}

	public List<BookMeetingRoomDTO> getUserBooking(String emailId){
		logger.info("In BookMeetingRoomImpl ....getUserBooking()....");

		List<BookMeetingRoom> list = bookMeetingRoomDAO.getUserBooking(emailId);


		return BookMeetingRoomUtil.convertDaoListToDto(list);
	}

	@Override
	public List<MeetingRoomDTO> getAvailableMeetingRoom(int locationId, Timestamp beginTime, Timestamp endTime) {
		logger.info("In BookMeetingRoomImpl...getAvailableMeetingRoom()....");


		List<MeetingRoom> list = bookMeetingRoomDAO.getAvailableMeetingRoom(locationId, beginTime, endTime);


		return MeetingRoomUtil.convertDaoListToDto(list);
	}



}
