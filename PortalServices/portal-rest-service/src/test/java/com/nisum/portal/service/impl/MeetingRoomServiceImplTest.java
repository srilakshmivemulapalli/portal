package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.MeetingRoomDAO;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.service.dto.MeetingRoomDTO;

@RunWith(MockitoJUnitRunner.class)
public class MeetingRoomServiceImplTest {

	@InjectMocks
	MeetingRoomImpl meetingRoomServiceImpl;

	@Mock
	MeetingRoomDAO meetingRoomDAOImpl;
	
	MeetingRoom meetingRoom;
	
	MeetingRoomDTO meetingRoomDTO;
	
	@Before
	public void setUp() {
		meetingRoom = new MeetingRoom();
		meetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setDescription("description");
		meetingRoom.setLocation(1);
		meetingRoom.setStartDate(new Timestamp(System.currentTimeMillis()));

		meetingRoomDTO = new MeetingRoomDTO();
		meetingRoomDTO.setBeginTime(meetingRoom.getBeginTime());
		meetingRoomDTO.setEndTime(meetingRoom.getEndTime());
		meetingRoomDTO.setMeetingRoomId(meetingRoom.getMeetingRoomId());
		meetingRoomDTO.setDescription(meetingRoom.getDescription());
		meetingRoomDTO.setLocationId(1);
		meetingRoomDTO.setStartDate(meetingRoom.getStartDate());
	}

	@Test
	public void getAllMeetingRoomTest() {

		int locationId = 1;
		List<MeetingRoomDTO> expected = new ArrayList<>();
		List<MeetingRoom> meetingRoomList = new ArrayList<>();

		meetingRoomList.add(meetingRoom);

		expected.add(meetingRoomDTO);

		when(meetingRoomDAOImpl.findAllByLocationId(locationId)).thenReturn(meetingRoomList);

		List<MeetingRoomDTO> meetingRoomActual = meetingRoomServiceImpl.getAllMeetingRoom(locationId);
			
	}
	
	
	@Test
	public void registerMeetingRoomSaveTest() {
		String expected="Saved Successfully...";
		
		when(meetingRoomDAOImpl.findByMeetingRoomId(meetingRoom.getMeetingRoomId())).thenReturn(null);
		
		String actual=meetingRoomServiceImpl.registerMeetingRoom(meetingRoomDTO);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void registerMeetingRoomUpdateTest() {
		String expected="Updated Successfully...";
		
		when(meetingRoomDAOImpl.findByMeetingRoomId(meetingRoom.getMeetingRoomId())).thenReturn(meetingRoom);
		
		String actual=meetingRoomServiceImpl.registerMeetingRoom(meetingRoomDTO);
		
		assertEquals(expected, actual);
		
	}
	
	
}
