package com.nisum.portal.data.dao.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.data.repository.BookMeetingRoomRepository;
import com.nisum.portal.data.repository.MeetingRoomRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookMeetingRoomDAOImplTest {
    @InjectMocks
    BookMeetingRoomDAOImpl bookMeetingRoomDAOImpl;
    
    @Mock
	BookMeetingRoomRepository bookMeetingRoomRepository;
	
    BookMeetingRoom bookMeetingRoom;
    

	@Mock
	MeetingRoomRepository meetingRoomRepository;

	MeetingRoom meetingRoom;
	
	@Mock
	List<MeetingRoom> meetingRoomExpectedList;
	
	@Mock
	List<BookMeetingRoom> bookMeetingRoomExpectedList;
	
	
	@Before
	public void setUp() {
		meetingRoom = new MeetingRoom();
		
		meetingRoom.setLocation(1);
		meetingRoom.setMeetingRoomId(1);
		meetingRoom.setMeetingRoomName("description");
		
		bookMeetingRoom=new BookMeetingRoom();
		bookMeetingRoom.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setBookMeetingRoomId(1);
		bookMeetingRoom.setEmailId("radhi@nisum.com");
		bookMeetingRoom.setHeadCount(20);
		bookMeetingRoom.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoom.setMeetingTitle("scrum");
		 Location location=new Location();
		 location.setLocationId(1);
		 //location.setLocationName("anu");
		
		bookMeetingRoom.setLocation(location);
		bookMeetingRoom.setMeetingRoom(meetingRoom);
		
		
		}
	
	@Test
	public void getAvailableMeetingRoomTest() {
		int locationId=1;
		Timestamp beginTime=new Timestamp(System.currentTimeMillis());
		Timestamp endTime=new Timestamp(System.currentTimeMillis());
		
		meetingRoomExpectedList.add(meetingRoom);
		when(meetingRoomRepository.getAvailableMeetingRoom(locationId, beginTime, endTime)).thenReturn(meetingRoomExpectedList);
		List<MeetingRoom> actual = bookMeetingRoomDAOImpl.getAvailableMeetingRoom(1,  beginTime,  endTime);
		assertEquals(meetingRoomExpectedList, actual);
	}
	
	@Test
    public void saveBookMeetingTest(){

		when(bookMeetingRoomRepository.save(bookMeetingRoom)).thenReturn(bookMeetingRoom);

		 BookMeetingRoom actual = bookMeetingRoomDAOImpl.save(bookMeetingRoom);

		assertEquals(bookMeetingRoom.getEmailId(), actual.getEmailId());
    	
    	
    }
	
@Test
public void findByBookMeetingRoomIdTest() {
	
	int bookMeetingRoomId=1;

	when(bookMeetingRoomRepository.findOne(bookMeetingRoomId)).thenReturn(bookMeetingRoom);
	BookMeetingRoom actual = bookMeetingRoomDAOImpl.findByBookMeetingRoomId(bookMeetingRoomId);

	assertEquals(bookMeetingRoom, actual);

}
@Test
public void getUserBookingTest() {
	String emailId="radhi@nisum.com";
	
	bookMeetingRoomExpectedList.add(bookMeetingRoom);
	when(bookMeetingRoomRepository.getUserBooking(emailId)).thenReturn(bookMeetingRoomExpectedList);
	List<BookMeetingRoom> actual = bookMeetingRoomDAOImpl.getUserBooking(emailId);
	assertEquals(bookMeetingRoomExpectedList, actual);
	
	
}


}
