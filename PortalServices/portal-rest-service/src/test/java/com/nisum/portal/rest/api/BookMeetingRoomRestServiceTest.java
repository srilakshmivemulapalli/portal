package com.nisum.portal.rest.api;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.portal.service.api.BookMeetingRoomService;
import com.nisum.portal.service.api.LocationService;
import com.nisum.portal.service.api.MeetingRoomService;
import com.nisum.portal.service.dto.BookMeetingRoomDTO;
import com.nisum.portal.service.dto.LocationDTO;
import com.nisum.portal.service.dto.MeetingRoomDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.BookMeetingRoomRestServiceException;

import com.nisum.portal.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class BookMeetingRoomRestServiceTest {
	
	@Mock
	BookMeetingRoomService bookMeetingRoomService;
	
	@Mock
     MeetingRoomService meetingRoomService;
	
	@Mock
	LocationService locationService;
	
	@InjectMocks
	BookMeetingRoomRestService bookMeetingRoomRestService;
	
	@InjectMocks
	BookMeetingRoomRestServiceException bookMeetingRoomException;
	
	
	
	

	@Test
	public void registerMeetingRoomTest() throws BookMeetingRoomRestServiceException  {
		
		MeetingRoomDTO meetingRoomDTO=new MeetingRoomDTO();
		meetingRoomDTO.setMeetingRoomName("meeting");
		meetingRoomDTO.setLocationId(10);
		meetingRoomDTO.setMeetingRoomId(20);
		
		
		 String value = "saved succesfully";
		 ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		 serviceStatusDto.setMessage(value);
		 
		Mockito.when(meetingRoomService.registerMeetingRoom(meetingRoomDTO)).thenReturn(value);
		ResponseEntity<ServiceStatusDto> expected=new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
		ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) bookMeetingRoomRestService.registerMeetingRoom(meetingRoomDTO);
		
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertThat(actual.getBody()).isEqualToComparingFieldByField(expected.getBody());
	
		 
	}
	@Test(expected = Exception.class)
	public void registerMeetingRoomException() throws BookMeetingRoomRestServiceException {
		when(bookMeetingRoomRestService.registerMeetingRoom(null)).thenThrow(bookMeetingRoomException);
	}
	
	/*@Test
	public void getAllMeetingRoomTest() throws BookMeetingRoomRestServiceException {
		List<MeetingRoomDTO> meetingList = new ArrayList<MeetingRoomDTO>();
		MeetingRoomDTO meetingRoomDTO=new MeetingRoomDTO();
		meetingRoomDTO.setMeetingRoomName("meeting");
		meetingRoomDTO.setLocationId(10);
		meetingRoomDTO.setMeetingRoomId(20);
		
		 
		
		meetingList.add(meetingRoomDTO);
		
		ResponseEntity<List<MeetingRoomDTO>> expected=new ResponseEntity<List<MeetingRoomDTO>>(meetingList, HttpStatus.OK);
		when(meetingRoomService.getAllMeetingRoom(10,new Timestamp(System.currentTimeMillis()))).thenReturn(meetingList);
		ResponseEntity<List<MeetingRoomDTO>> actual = (ResponseEntity<List<MeetingRoomDTO>>) bookMeetingRoomRestService.getAllMeetingRoom(10,new Timestamp(System.currentTimeMillis()));
	        assertEquals(expected, actual);	
	}
	
	@Test
	public void getAllMeetingRoomEmpty() throws BookMeetingRoomRestServiceException {
		List<MeetingRoomDTO> meetingList = new ArrayList<MeetingRoomDTO>();
		MeetingRoomDTO meetingRoomDTO=new MeetingRoomDTO();
		meetingRoomDTO.setMeetingRoomName("meeting");
		meetingRoomDTO.setLocationId(10);
		meetingRoomDTO.setMeetingRoomId(20);
		
		
		//meetingList.add(meetingRoomDTO);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setMessage(Constants.MEETINGS_EMPTY);
		
		ResponseEntity<ServiceStatusDto> expected=new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NO_CONTENT);
		when(meetingRoomService.getAllMeetingRoom(10,new Timestamp(System.currentTimeMillis()))).thenReturn(meetingList);
		ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) bookMeetingRoomRestService.getAllMeetingRoom(10,new Timestamp(System.currentTimeMillis()));
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertThat(expected.getBody()).isEqualToComparingFieldByField(actual.getBody());
	        
	}
	@Test(expected = Exception.class)
	public void getAllMeetingRoomException() throws BookMeetingRoomRestServiceException{
		when(bookMeetingRoomRestService.getAllMeetingRoom(0,null)).thenThrow(bookMeetingRoomException);
	}*/
	
	@Test
	public void getAvailableMeetingRoomTest() throws BookMeetingRoomRestServiceException {
		List<MeetingRoomDTO> meetingList = new ArrayList<MeetingRoomDTO>();
		MeetingRoomDTO meetingRoomDTO=new MeetingRoomDTO();
		meetingRoomDTO.setMeetingRoomName("meeting");
		meetingRoomDTO.setLocationId(10);
		meetingRoomDTO.setMeetingRoomId(20);
		
		
		meetingList.add(meetingRoomDTO);
		
		ResponseEntity<List<MeetingRoomDTO>> expected=new ResponseEntity<List<MeetingRoomDTO>>(meetingList, HttpStatus.OK);
		when(bookMeetingRoomService.getAvailableMeetingRoom(10,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()))).thenReturn(meetingList);
		ResponseEntity<List<MeetingRoomDTO>> actual = (ResponseEntity<List<MeetingRoomDTO>>) bookMeetingRoomRestService.getAvailableMeetingRoom(10,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
	        assertEquals(expected, actual);	
		
		
	}
	
	@Test
	public void getAvailableMeetingRoomEmptyTest() throws BookMeetingRoomRestServiceException {
		List<MeetingRoomDTO> meetingList = new ArrayList<MeetingRoomDTO>();
		MeetingRoomDTO meetingRoomDTO=new MeetingRoomDTO();
		meetingRoomDTO.setMeetingRoomName("meeting");
		meetingRoomDTO.setLocationId(10);
		meetingRoomDTO.setMeetingRoomId(20);
		
		 
		
		//meetingList.add(meetingRoomDTO);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setMessage(Constants.MEETINGS_ROOMS_EMPTY);
		
		ResponseEntity<ServiceStatusDto> expected=new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NO_CONTENT);
		when(bookMeetingRoomService.getAvailableMeetingRoom(10,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()))).thenReturn(meetingList);
		ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) bookMeetingRoomRestService.getAvailableMeetingRoom(10,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
	
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertThat(expected.getBody()).isEqualToComparingFieldByField(actual.getBody());
		
		
		
	}
	@Test(expected = Exception.class)
	public void getAvailableMeetingRoomException() throws BookMeetingRoomRestServiceException{
		when(bookMeetingRoomRestService.getAvailableMeetingRoom(0, null, null)).thenThrow(bookMeetingRoomException);
	}
	
  @Test
  public void bookMeetingRoomTest() throws BookMeetingRoomRestServiceException {
	  
		
		BookMeetingRoomDTO bookMeetingRoomDTO=new BookMeetingRoomDTO();
		bookMeetingRoomDTO.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookMeetingRoomId(1);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEmailId("radhi@nisum.com");
		
		String value = "saved succesfully";
		 ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		 serviceStatusDto.setMessage(value);
		 
		Mockito.when(bookMeetingRoomService.bookMeetingRoom(bookMeetingRoomDTO)).thenReturn(value);
		ResponseEntity<ServiceStatusDto> expected=new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
		ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) bookMeetingRoomRestService.bookMeetingRoom(bookMeetingRoomDTO);
		
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertThat(actual.getBody()).isEqualToComparingFieldByField(expected.getBody());
		
		
	}
  
  @Test(expected = Exception.class)
	public void bookMeetingRoomException() throws BookMeetingRoomRestServiceException{
		when(bookMeetingRoomRestService.bookMeetingRoom(null)).thenThrow(bookMeetingRoomException);
	}
	
  @Test
  public void getUserBookingTest() throws BookMeetingRoomRestServiceException {
	  List<BookMeetingRoomDTO> userBookingList = new ArrayList<BookMeetingRoomDTO>();
	  BookMeetingRoomDTO bookMeetingRoomDTO=new BookMeetingRoomDTO();
		bookMeetingRoomDTO.setBeginTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookingDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setBookMeetingRoomId(1);
		bookMeetingRoomDTO.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEndTime(new Timestamp(System.currentTimeMillis()));
		bookMeetingRoomDTO.setEmailId("radhi@nisum.com");
		userBookingList.add(bookMeetingRoomDTO);
		

		ResponseEntity<List<BookMeetingRoomDTO>> expected=new ResponseEntity<List<BookMeetingRoomDTO>>(userBookingList, HttpStatus.OK);
		when(bookMeetingRoomService.getUserBooking("radhi@nisum.com")).thenReturn(userBookingList);
		ResponseEntity<List<BookMeetingRoomDTO>> actual = (ResponseEntity<List<BookMeetingRoomDTO>>) bookMeetingRoomRestService.getUserBooking("radhi@nisum.com");
	        assertEquals(expected, actual);	
		
		
		 }
        @Test 
        public void getUserBookingTestEmpty() throws BookMeetingRoomRestServiceException {
        	 List<BookMeetingRoomDTO> userBookingList = new ArrayList<BookMeetingRoomDTO>();
        	 
        	 ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
     		serviceStatusDto.setMessage(Constants.USER_BOOKING_EMPTY);
     		
     		ResponseEntity<ServiceStatusDto> expected=new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NO_CONTENT);
     		when(bookMeetingRoomService.getUserBooking("radhi@nisum.com")).thenReturn(userBookingList);
     		ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) bookMeetingRoomRestService.getUserBooking("radhi@nisum.com");
     	
     		assertEquals(expected.getStatusCode(), actual.getStatusCode());
     		assertThat(expected.getBody()).isEqualToComparingFieldByField(actual.getBody());

        	
        }
        @Test(expected = Exception.class)
    	public void getUserBookingException() throws BookMeetingRoomRestServiceException{
    		when(bookMeetingRoomRestService.getUserBooking(null)).thenThrow(bookMeetingRoomException);
    	} 
        
  @Test
  public void registerLocationTest() throws BookMeetingRoomRestServiceException {
	  LocationDTO locationDTO=new LocationDTO();
	  locationDTO.setLocationId(10);
	  locationDTO.setLocationName("hyd");
	  
	  String value = "saved succesfully";
		 ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		 serviceStatusDto.setMessage(value);
		 
		Mockito.when(locationService.registerLocation(locationDTO)).thenReturn(value);
		ResponseEntity<ServiceStatusDto> expected=new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
		ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) bookMeetingRoomRestService.registerLocation(locationDTO);
		
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertThat(actual.getBody()).isEqualToComparingFieldByField(expected.getBody());
	  
  }
  
  @Test(expected = Exception.class)
	public void registerLocationException() throws BookMeetingRoomRestServiceException{
		when(bookMeetingRoomRestService.registerLocation(null)).thenThrow(bookMeetingRoomException);
	} 
  
  @Test
  public void getAllLocationTest() throws BookMeetingRoomRestServiceException {
	  List<LocationDTO> locationList=new  ArrayList<LocationDTO>();
	  LocationDTO locationDTO=new LocationDTO();
	  locationDTO.setLocationId(10);
	  locationDTO.setLocationName("hyd");
	  locationList.add(locationDTO);
	  
	  ResponseEntity<List<LocationDTO>> expected=new ResponseEntity<List<LocationDTO>>(locationList, HttpStatus.OK);
		when(locationService.getAllLocation()).thenReturn(locationList);
		ResponseEntity<List<LocationDTO>> actual = (ResponseEntity<List<LocationDTO>>) bookMeetingRoomRestService.getAllLocation();
	        assertEquals(expected, actual);	
	  
  }
  
  @Test
  public void getAllLocationTestEmpty() throws BookMeetingRoomRestServiceException {
	  List<LocationDTO> locationList=new  ArrayList<LocationDTO>();
	  
	  ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setMessage(Constants.LOCATIONS_EMPTY);
		
		ResponseEntity<ServiceStatusDto> expected=new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NO_CONTENT);
		when(locationService.getAllLocation()).thenReturn(locationList);
		ResponseEntity<ServiceStatusDto> actual = (ResponseEntity<ServiceStatusDto>) bookMeetingRoomRestService.getAllLocation();
	
		assertEquals(expected.getStatusCode(), actual.getStatusCode());
		assertThat(expected.getBody()).isEqualToComparingFieldByField(actual.getBody());

	  
	  
  }
  @Test(expected = Exception.class)
	public void getAllLocationException() throws BookMeetingRoomRestServiceException{
		when(bookMeetingRoomRestService.getAllLocation()).thenThrow(bookMeetingRoomException);
	} 
  
  

}
