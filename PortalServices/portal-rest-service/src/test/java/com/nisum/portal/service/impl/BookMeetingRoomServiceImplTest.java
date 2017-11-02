package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.BookMeetingRoomDAO;
import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.data.repository.BookMeetingRoomRepository;
import com.nisum.portal.data.repository.LocationRepository;
import com.nisum.portal.data.repository.MeetingRoomRepository;
import com.nisum.portal.service.dto.BookMeetingRoomDTO;
import com.nisum.portal.service.dto.MeetingRoomDTO;

@RunWith(MockitoJUnitRunner.class)
public class BookMeetingRoomServiceImplTest {

	@InjectMocks
	BookMeetingRoomImpl bookMeetingRoomServiceImpl;
	
	
	@Mock
	BookMeetingRoomDAO bookMeetingRoomDAOImpl;
	
	@Mock
	LocationRepository locationRepository;
	
	@Mock 
	BookMeetingRoomRepository bookMeetingRoomRepository;
	
	@Mock 
	MeetingRoomRepository meetingRoomRepository;
	
	
	@Test
	public void bookMeetingRoomSave() {

	
		String expected = "Saved Successfully...";
		BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();

		bookMeetingRoomDTO.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookMeetingRoomId(0);
		bookMeetingRoomDTO.setMeetingRoomId(0);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setMeetingTitle("meetingtitle");
		bookMeetingRoomDTO.setEmailId("test@nisum.com");
		bookMeetingRoomDTO.setLocationId(1);
		bookMeetingRoomDTO.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setHeadCount(10);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setMeetingRoomId(0);
		meetingRoom.setDescription("description");
		meetingRoom.setLocation(1);
		meetingRoom.setStartDate(new Timestamp(System.currentTimeMillis()));

		Location location = new Location();
		location.setLocationId(1);
		location.setLocationName("INDIA");

		BookMeetingRoom bookMeetingRoom = new BookMeetingRoom();
		bookMeetingRoom.setBookMeetingRoomId(0);
		bookMeetingRoom.setMeetingTitle("testTitle");
		bookMeetingRoom.setEmailId("test@nisum.com");
		bookMeetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setHeadCount(20);
		bookMeetingRoom.setMeetingRoom(meetingRoom);
		bookMeetingRoom.setLocation(location);

		when(meetingRoomRepository.findOne(bookMeetingRoomDTO.getMeetingRoomId())).thenReturn(meetingRoom);
		when(locationRepository.findOne(1)).thenReturn(location);
		when(bookMeetingRoomRepository.getMeetingRoomForTimePeriod(bookMeetingRoomDTO.getBeginTime(),
				bookMeetingRoomDTO.getEndTime())).thenReturn(null);
		when(bookMeetingRoomDAOImpl.save(bookMeetingRoom)).thenReturn(bookMeetingRoom);
		when(bookMeetingRoomDAOImpl.findByBookMeetingRoomId(bookMeetingRoomDTO.getBookMeetingRoomId()))
				.thenReturn(bookMeetingRoom);
		String actual = bookMeetingRoomServiceImpl.bookMeetingRoom(bookMeetingRoomDTO);

		assertEquals(expected, actual);

	}

	@Test
	public void bookMeetingRoomUpdate() {

		String expected = "updated Successfully...";
		BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();

		bookMeetingRoomDTO.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookMeetingRoomId(1);
		bookMeetingRoomDTO.setMeetingRoomId(1);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setMeetingTitle("meetingtitle");
		bookMeetingRoomDTO.setEmailId("test@nisum.com");
		bookMeetingRoomDTO.setLocationId(1);
		bookMeetingRoomDTO.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setHeadCount(10);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setDescription("description");
		meetingRoom.setLocation(1);
		meetingRoom.setStartDate(new Timestamp(System.currentTimeMillis()));

		Location location = new Location();
		location.setLocationId(1);
		location.setLocationName("INDIA");

		BookMeetingRoom bookMeetingRoom = new BookMeetingRoom();
		bookMeetingRoom.setBookMeetingRoomId(1);
		bookMeetingRoom.setMeetingTitle("testTitle");
		bookMeetingRoom.setEmailId("test@nisum.com");
		bookMeetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setHeadCount(20);
		bookMeetingRoom.setMeetingRoom(meetingRoom);
		bookMeetingRoom.setLocation(location);

		when(meetingRoomRepository.findOne(bookMeetingRoomDTO.getMeetingRoomId())).thenReturn(meetingRoom);
		when(locationRepository.findOne(1)).thenReturn(location);
		when(bookMeetingRoomRepository.getMeetingRoomForTimePeriod(bookMeetingRoomDTO.getBeginTime(),
				bookMeetingRoomDTO.getEndTime())).thenReturn(null);
		when(bookMeetingRoomDAOImpl.save(bookMeetingRoom)).thenReturn(bookMeetingRoom);

		String actual = bookMeetingRoomServiceImpl.bookMeetingRoom(bookMeetingRoomDTO);

		assertEquals(expected, actual);

	}

	@Test
	public void bookMeetingRoomExistsAlready() {

		String expected = "Meeting alredy booked by someone";
		BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();

		bookMeetingRoomDTO.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookMeetingRoomId(1);
		bookMeetingRoomDTO.setMeetingRoomId(1);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setMeetingTitle("meetingtitle");
		bookMeetingRoomDTO.setEmailId("test@nisum.com");
		bookMeetingRoomDTO.setLocationId(1);
		bookMeetingRoomDTO.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setHeadCount(10);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setDescription("description");
		meetingRoom.setLocation(1);
		meetingRoom.setStartDate(new Timestamp(System.currentTimeMillis()));

		Location location = new Location();
		location.setLocationId(1);
		location.setLocationName("INDIA");

		BookMeetingRoom bookMeetingRoom = new BookMeetingRoom();
		bookMeetingRoom.setBookMeetingRoomId(1);
		bookMeetingRoom.setMeetingTitle("testTitle");
		bookMeetingRoom.setEmailId("test@nisum.com");
		bookMeetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setHeadCount(20);
		bookMeetingRoom.setMeetingRoom(meetingRoom);
		bookMeetingRoom.setLocation(location);

		when(meetingRoomRepository.findOne(bookMeetingRoomDTO.getMeetingRoomId())).thenReturn(meetingRoom);
		when(locationRepository.findOne(1)).thenReturn(location);
		when(bookMeetingRoomRepository.getMeetingRoomForTimePeriod(bookMeetingRoomDTO.getBeginTime(),
				bookMeetingRoomDTO.getEndTime())).thenReturn(bookMeetingRoom);

		String actual = bookMeetingRoomServiceImpl.bookMeetingRoom(bookMeetingRoomDTO);

		assertEquals(expected, actual);
	}

	@Test
	public void bookMeetingRoomNotFound() {

		String expected = "Location or meeting Id not found";
		BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();

		bookMeetingRoomDTO.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookMeetingRoomId(0);
		bookMeetingRoomDTO.setMeetingRoomId(0);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setMeetingTitle("meetingtitle");
		bookMeetingRoomDTO.setEmailId("test@nisum.com");
		bookMeetingRoomDTO.setLocationId(1);
		bookMeetingRoomDTO.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setHeadCount(10);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

		when(meetingRoomRepository.findOne(bookMeetingRoomDTO.getMeetingRoomId())).thenReturn(null);
		when(locationRepository.findOne(bookMeetingRoomDTO.getLocationId())).thenReturn(null);

		String actual = bookMeetingRoomServiceImpl.bookMeetingRoom(bookMeetingRoomDTO);

		assertEquals(expected, actual);

	}

	@Test
	public void getUserBooking() {

		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setDescription("description");
		meetingRoom.setLocation(1);
		meetingRoom.setStartDate(new Timestamp(System.currentTimeMillis()));
		Location location = new Location();
		location.setLocationId(1);
		location.setLocationName("INDIA");

		BookMeetingRoom bookMeetingRoom = new BookMeetingRoom();
		bookMeetingRoom.setBookMeetingRoomId(1);
		bookMeetingRoom.setMeetingTitle("testTitle");
		bookMeetingRoom.setEmailId("test@nisum.com");
		bookMeetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setHeadCount(20);
		bookMeetingRoom.setMeetingRoom(meetingRoom);
		bookMeetingRoom.setLocation(location);

		List<BookMeetingRoom> bookMeetingRoomList = new ArrayList<>();
		bookMeetingRoomList.add(bookMeetingRoom);

		List<BookMeetingRoomDTO> expected = new ArrayList<>();

		BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();

		bookMeetingRoomDTO.setBeginTime(bookMeetingRoom.getBeginTime());
		bookMeetingRoomDTO.setEndTime(bookMeetingRoom.getEndTime());
		bookMeetingRoomDTO.setBookMeetingRoomId(bookMeetingRoom.getBookMeetingRoomId());
		bookMeetingRoomDTO.setMeetingRoomId(1);
		bookMeetingRoomDTO.setCreatedDate(bookMeetingRoom.getCreatedDate());
		bookMeetingRoomDTO.setMeetingTitle(bookMeetingRoom.getMeetingTitle());
		bookMeetingRoomDTO.setEmailId(bookMeetingRoom.getEmailId());
		bookMeetingRoomDTO.setLocationId(1);
		bookMeetingRoomDTO.setBookingDate(bookMeetingRoom.getBookingDate());
		bookMeetingRoomDTO.setHeadCount(bookMeetingRoom.getHeadCount());
		bookMeetingRoomDTO.setCreatedDate(bookMeetingRoom.getCreatedDate());

		expected.add(bookMeetingRoomDTO);

		when(bookMeetingRoomDAOImpl.getUserBooking("test@nisum.com")).thenReturn(bookMeetingRoomList);

		List<BookMeetingRoomDTO> actual = bookMeetingRoomServiceImpl.getUserBooking("test@nisum.com");

		assertEquals(expected, actual);
	}

	@Test
	public void getAvailableMeetingRoomTest() {
		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setDescription("description");
		meetingRoom.setLocation(1);
		meetingRoom.setStartDate(new Timestamp(System.currentTimeMillis()));
		List<MeetingRoom> meetingRoomList = new ArrayList<>();

		meetingRoomList.add(meetingRoom);

		MeetingRoomDTO meetingRoomDTO = new MeetingRoomDTO();
		meetingRoomDTO.setBeginTime(meetingRoom.getBeginTime());
		meetingRoomDTO.setEndTime(meetingRoom.getEndTime());
		meetingRoomDTO.setMeetingRoomId(meetingRoom.getMeetingRoomId());
		meetingRoomDTO.setDescription(meetingRoom.getDescription());
		meetingRoomDTO.setLocationId(1);
		meetingRoomDTO.setStartDate(meetingRoom.getStartDate());

		List<MeetingRoomDTO> expected = new ArrayList<>();
		expected.add(meetingRoomDTO);

		int locationId = 1;
		Timestamp beginTime = new Timestamp(System.currentTimeMillis());
		Timestamp endTime = new Timestamp(System.currentTimeMillis() * 30L);

		when(bookMeetingRoomDAOImpl.getAvailableMeetingRoom(locationId, beginTime, endTime))
				.thenReturn(meetingRoomList);

		List<MeetingRoomDTO> actual = bookMeetingRoomServiceImpl.getAvailableMeetingRoom(locationId, beginTime,
				endTime);

		assertEquals(expected, actual);
	}
	
}
