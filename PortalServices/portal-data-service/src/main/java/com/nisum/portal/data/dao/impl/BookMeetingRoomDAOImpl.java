package com.nisum.portal.data.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.BookMeetingRoomDAO;
import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.data.repository.BookMeetingRoomRepository;
import com.nisum.portal.data.repository.MeetingRoomRepository;

@Configuration
public class BookMeetingRoomDAOImpl implements BookMeetingRoomDAO{
	
private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BookMeetingRoomRepository bookMeetingRoomRepository;
	
	@Autowired
	MeetingRoomRepository  meetingRoomRepository;
	
public List<MeetingRoom> getAvailableMeetingRoom(int locationId, Timestamp beginTime, Timestamp endTime){
	logger.info("In BookMeetingRoomDAOImpl class....getAvailableMeetingRoom()....");
	 return meetingRoomRepository.getAvailableMeetingRoom(locationId, beginTime, endTime);
		
		
}

public BookMeetingRoom bookMeetingRoom(BookMeetingRoom bookMeetingRoom){
	logger.info("In bookMeetingRoom()..BookMeetingRoomDAOImpl...");
	return bookMeetingRoomRepository.save(bookMeetingRoom);
			
}

public  List<BookMeetingRoom> getUserBooking(String emailId){
	logger.info("In BookMeetingRoomDAOImpl class....getUserBooking()....");
	return bookMeetingRoomRepository.getUserBooking(emailId);
		
	 
}

public BookMeetingRoom save(BookMeetingRoom bookMeetingRoom)
{
	logger.info("In BookMeetingRoomDAOImpl class....save()....");
	return  bookMeetingRoomRepository.save(bookMeetingRoom);
}

public BookMeetingRoom findByBookMeetingRoomId(int bookMeetingRoomId) {
	
	logger.info("In BookMeetingRoomDAOImpl class....findByBookMeetingRoomId()....");
	return bookMeetingRoomRepository.findOne(bookMeetingRoomId);
	
	
}



}
