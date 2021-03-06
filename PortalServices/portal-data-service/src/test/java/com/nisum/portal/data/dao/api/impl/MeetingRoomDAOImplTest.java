package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.MeetingRoomDAOImpl;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.data.repository.MeetingRoomRepository;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRoomDAOImplTest {

	@InjectMocks
	MeetingRoomDAOImpl meetingRoomDAOImpl;

	@Mock
	MeetingRoomRepository meetingRoomRepository;

	MeetingRoom meetingRoom;

	@Mock
	List<MeetingRoom> meetingRoomExpectedList;

	@Before
	public void setUp() {
		meetingRoom = new MeetingRoom();
		
		meetingRoom.setLocation(1);
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setMeetingRoomName("description");
	}

	@Test
	public void findByMeetingRoomIdTest() {

		int meetingRoomId = 1;

		//when(meetingRoomRepository.findOne(meetingRoomId)).thenReturn(meetingRoom);

		MeetingRoom actual = meetingRoomDAOImpl.findByMeetingRoomId(meetingRoomId);

		assertEquals(meetingRoom, actual);
	}

	@Test
	public void saveMeetingRoomTest() {

		MeetingRoom meetingRoom = new MeetingRoom();
		
		meetingRoom.setLocation(1);
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setMeetingRoomName("description");

		//when(meetingRoomRepository.save(meetingRoom)).thenReturn(meetingRoom);

		MeetingRoom actual = meetingRoomDAOImpl.save(meetingRoom);

		assertEquals(meetingRoom, actual);

	}

	@Test
	public void findAllByLocationId() {

		int locationId = 1;
		

		meetingRoomExpectedList.add(meetingRoom);

		//when(meetingRoomRepository.findAllByLocationIdAndDate(locationId, new Timestamp(System.currentTimeMillis()))).thenReturn(meetingRoomExpectedList);
	//	List<MeetingRoom> actual = meetingRoomDAOImpl.findAllByLocationIdAndDate(locationId, new Timestamp(System.currentTimeMillis()));

		//assertEquals(meetingRoomExpectedList, actual);

	}
	
}
