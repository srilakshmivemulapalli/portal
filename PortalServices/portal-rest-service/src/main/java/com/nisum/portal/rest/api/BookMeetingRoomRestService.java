package com.nisum.portal.rest.api;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.BookMeetingRoomService;
import com.nisum.portal.service.api.LocationService;
import com.nisum.portal.service.api.MeetingRoomService;
import com.nisum.portal.service.dto.BookMeetingRoomDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.LocationDTO;
import com.nisum.portal.service.dto.MeetingRoomDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.BookMeetingRoomRestServiceException;
import com.nisum.portal.util.BookMeetingRoomUtil;
import com.nisum.portal.util.Constants;

@RestController
@RequestMapping(value = "/v1/meetings")
public class BookMeetingRoomRestService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BookMeetingRoomService bookMeetingRoomService;
	
	@Autowired
	MeetingRoomService meetingRoomService;
	
	@Autowired 
	LocationService locationService;
	
	@RequestMapping(value = "/registerMeetingRoom", method = RequestMethod.POST)
	public ResponseEntity<?> registerMeetingRoom(@RequestBody MeetingRoomDTO meetingRoom) throws BookMeetingRoomRestServiceException{
		logger.info(".....In registerMeetingRoom() controller...");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			String message = meetingRoomService.registerMeetingRoom(meetingRoom);
			
			serviceStatusDto.setMessage(message);
		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
	}catch(Exception e){
		throw new BookMeetingRoomRestServiceException(Constants.INTERNALSERVERERROR);
	}
}		
	
	@RequestMapping(value = "/getAllbookedMeetingRoom", method = RequestMethod.GET)
	public ResponseEntity<?> getAllMeetingRoom(@QueryParam("locationnId") String locationnId,@QueryParam("startedDate") String startedDate,@QueryParam("startTime") String startTime) throws BookMeetingRoomRestServiceException{
		logger.info(".....In getAllMeetingRoom() controller...");
		
		 
		
		String startDate1=BookMeetingRoomUtil.getFormatedDate(startedDate) + BookMeetingRoomUtil.getFormatedTime(startedDate);
	/*	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		try {
		Date date1 = df.parse(startedDate);
		DateFormat outputFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		startDate1 = outputFormatter1.format(date1); //
		} catch (ParseException e) {
		e.printStackTrace();
		}
		
		*/
		//System.out.println(BookMeetingRoomUtil.getFormatedDate(startedDate) +" FROM FORMATED "+ BookMeetingRoomUtil.getFormatedTime(startedDate));
		System.out.println("Direct "+startDate1);

		
		Timestamp startDate = Timestamp.valueOf(startDate1);
		int locationId = 0;
		if (locationnId != null) {
			 locationId= Integer.parseInt(locationnId);
		}
		 
		try {
			
			List<MeetingRoomDTO> meetingsList = meetingRoomService.getAllMeetingRoom(locationId,startDate);
			if (meetingsList.size() == 0) {
				ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
				serviceStatusDto.setMessage(Constants.MEETINGS_EMPTY);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<MeetingRoomDTO>>(meetingsList, HttpStatus.OK);
			}
		} catch(Exception e) {
			throw new BookMeetingRoomRestServiceException(Constants.INTERNALSERVERERROR);
		}
	}
	
	
	@RequestMapping(value = "/getAvailableMeetingRoom/{locationId}/{beginTime}/{endTime}", method = RequestMethod.GET)
	public ResponseEntity<?> getAvailableMeetingRoom(@PathVariable int locationId, @PathVariable Timestamp beginTime, @PathVariable Timestamp endTime) throws BookMeetingRoomRestServiceException{
		logger.info("In getAvailableMeetingRoom() controller....");
		try {
		List<MeetingRoomDTO> meetingList = bookMeetingRoomService.getAvailableMeetingRoom(locationId, beginTime, endTime);
		if(meetingList.size()==0) {
			Errors error = new Errors();
			error.setErrorMessage(Constants.MEETINGS_ROOMS_EMPTY);
			return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
			
		}else {
			return new ResponseEntity<List<MeetingRoomDTO>>(meetingList, HttpStatus.OK);
		}
		}catch(Exception e) {
			throw new BookMeetingRoomRestServiceException(Constants.INTERNALSERVERERROR);
		}	
		
		
	}
	
	@RequestMapping(value = "/bookMeetingRoom", method = RequestMethod.POST)
	public  ResponseEntity<?> bookMeetingRoom(@RequestBody BookMeetingRoomDTO bookMeetingRoom) throws BookMeetingRoomRestServiceException{
		logger.info(".....In bookMeetingRoom() controller...");
		String message = bookMeetingRoomService.bookMeetingRoom(bookMeetingRoom);
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
		serviceStatusDto.setMessage(message);
		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
		
		}catch(Exception e){
			throw new BookMeetingRoomRestServiceException(Constants.INTERNALSERVERERROR);
		}
	}
	
	@RequestMapping(value = "/getUserBooking/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserBooking(@PathVariable String emailId) throws BookMeetingRoomRestServiceException{
		logger.info("In getUserBooking() controller....");
		emailId = emailId.substring(0, emailId.indexOf("@"))+"@nisum.com";
		System.out.println("emaiId"+emailId);
		try {
		List<BookMeetingRoomDTO> bookingRoom = bookMeetingRoomService.getUserBooking(emailId);
		if(bookingRoom.size()==0) {
			ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
			serviceStatusDto.setMessage(Constants.USER_BOOKING_EMPTY);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NO_CONTENT);
			
		}else {
			return new ResponseEntity<List<BookMeetingRoomDTO>>(bookingRoom, HttpStatus.OK);
		}
			
		}catch(Exception e){
			throw new BookMeetingRoomRestServiceException(Constants.INTERNALSERVERERROR);
			
		}
		
		
	}
	
	@RequestMapping(value = "/registerLocation", method = RequestMethod.POST)
	public ResponseEntity<?> registerLocation(@RequestBody LocationDTO location) throws BookMeetingRoomRestServiceException{
		logger.info(".....In registerLocation() controller...");
		String message = locationService.registerLocation(location);
		ServiceStatusDto serviceStatusDto=new ServiceStatusDto();
		try {
		
			serviceStatusDto.setMessage(message);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
			
		}catch(Exception e){
			throw new BookMeetingRoomRestServiceException(Constants.INTERNALSERVERERROR,e);
		}
		
		
		
	}
	
	@RequestMapping(value = "/getAllLocation", method = RequestMethod.GET)
	public ResponseEntity<?> getAllLocation() throws BookMeetingRoomRestServiceException{
		logger.info(".....In getAllLocation() controller...");
		
		try {
		List<LocationDTO> locationList = locationService.getAllLocation();
		if(locationList.size()==0) {
			ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
			serviceStatusDto.setMessage(Constants.LOCATIONS_EMPTY);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NO_CONTENT);
			
		}else {
			
				return new ResponseEntity<List<LocationDTO>>(locationList, HttpStatus.OK);
			}
			
		}catch(Exception e){
			throw new BookMeetingRoomRestServiceException(Constants.INTERNALSERVERERROR);
		}
		
		
	}
	
	/**
	 * exceptionHandler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BookMeetingRoomRestServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors errors = new Errors();
		errors.setErrorCode("Error-Meetings");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}
	
	



}
