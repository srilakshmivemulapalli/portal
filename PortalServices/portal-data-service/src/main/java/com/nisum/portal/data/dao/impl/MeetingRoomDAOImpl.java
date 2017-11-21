package com.nisum.portal.data.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.MeetingRoomDAO;
import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.data.repository.BookMeetingRoomRepository;
import com.nisum.portal.data.repository.MeetingRoomRepository;

@Configuration
public class MeetingRoomDAOImpl implements MeetingRoomDAO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MeetingRoomRepository meetingRoomRepository;
	
	@Autowired
	BookMeetingRoomRepository bookMeetingRoomRepository;

	public MeetingRoom findByMeetingRoomId(int meetingRoomId) {
		logger.info("In MeetingRoomDAOImpl class....findByMeetingRoomId()....");
		return meetingRoomRepository.findOne(meetingRoomId);

	}

	public MeetingRoom save(MeetingRoom meetingRoom) {
		logger.info("In MeetingRoomDAOImpl class....save()....");
		return meetingRoomRepository.save(meetingRoom);

	}

	public List<BookMeetingRoom> findAllByLocationIdAndDate(int locationId, Timestamp startDate) {
		logger.info("In MeetingRoomDAOImpl class....findAllByLocationIdAndDate()....");
		if (locationId == 0) {
			return bookMeetingRoomRepository.findAllByDate(startDate);
		}
		return bookMeetingRoomRepository.findAllByLocationIdAndDate(locationId, startDate);

	}

}
