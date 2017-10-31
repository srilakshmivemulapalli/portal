package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.MeetingRoomDAO;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.data.repository.MeetingRoomRepository;

@Configuration
public class MeetingRoomDAOImpl implements MeetingRoomDAO {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

@Autowired	
 MeetingRoomRepository meetingRoomRepository;

	public MeetingRoom findByMeetingRoomId(int meetingRoomId) {
		logger.info("In MeetingRoomDAOImpl class....findByMeetingRoomId()....");
		return meetingRoomRepository.findOne(meetingRoomId);
		
	}
	
	public MeetingRoom save( MeetingRoom meetingRoom) {
		logger.info("In MeetingRoomDAOImpl class....save()....");
		return  meetingRoomRepository.save(meetingRoom);

	 }
	 
	 public List<MeetingRoom> findAllByLocationId(int locationId){
		 logger.info("In MeetingRoomDAOImpl class....findAllByLocationId()....");
		return  meetingRoomRepository.findAllByLocationId(locationId);
		 
	 }

}
