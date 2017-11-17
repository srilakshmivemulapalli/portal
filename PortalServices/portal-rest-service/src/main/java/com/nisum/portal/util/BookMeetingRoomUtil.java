package com.nisum.portal.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.domain.MeetingRoom;
import com.nisum.portal.service.dto.BookMeetingRoomDTO;

public class BookMeetingRoomUtil {

	public static List<BookMeetingRoomDTO> convertDaoListToDto(List<BookMeetingRoom> bookMeetingRoom) {
		List<BookMeetingRoomDTO> bookMeetingRoomDTOList = new ArrayList<BookMeetingRoomDTO>();

		if (CollectionUtils.isNotEmpty(bookMeetingRoom)) {
			for (BookMeetingRoom bookMeetingRoomDAO : bookMeetingRoom) {
				BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();
				bookMeetingRoomDTO.setBeginTime(bookMeetingRoomDAO.getBeginTime());
				bookMeetingRoomDTO.setBookMeetingRoomId(bookMeetingRoomDAO.getBookMeetingRoomId());

				bookMeetingRoomDTO.setEndTime(bookMeetingRoomDAO.getEndTime());
				bookMeetingRoomDTO.setHeadCount(bookMeetingRoomDAO.getHeadCount());
				// bookMeetingRoomDTO.setMeetingRoomDTO(bookMeetingRoomDAO.getMeetingRoom());
				bookMeetingRoomDTO.setMeetingTitle(bookMeetingRoomDAO.getMeetingTitle());

				bookMeetingRoomDTO.setEmailId(bookMeetingRoomDAO.getEmailId());
				bookMeetingRoomDTO.setLocationId(bookMeetingRoomDAO.getLocation().getLocationId());
				bookMeetingRoomDTO.setMeetingRoomId(bookMeetingRoomDAO.getMeetingRoom().getMeetingRoomId());

				bookMeetingRoomDTOList.add(bookMeetingRoomDTO);

			}
		}
		return bookMeetingRoomDTOList;
	}

	public static BookMeetingRoomDTO convertDaoObjectToDto(BookMeetingRoom bookMeetingRoom) {
		BookMeetingRoomDTO bookMeetingRoomDTO = new BookMeetingRoomDTO();
		bookMeetingRoomDTO.setBeginTime(bookMeetingRoom.getBeginTime());
		bookMeetingRoomDTO.setBookMeetingRoomId(bookMeetingRoom.getBookMeetingRoomId());

		bookMeetingRoomDTO.setEndTime(bookMeetingRoom.getEndTime());
		bookMeetingRoomDTO.setHeadCount(bookMeetingRoom.getHeadCount());
		bookMeetingRoomDTO.setMeetingTitle(bookMeetingRoom.getMeetingTitle());

		bookMeetingRoomDTO.setEmailId(bookMeetingRoom.getEmailId());
		bookMeetingRoomDTO.setLocationId(bookMeetingRoom.getLocation().getLocationId());

		// MeetingRoomDTO meetingRoomDTO=new MeetingRoomDTO();
		// meetingRoomDTO.setDescription(bookMeetingRoom.getMeetingRoom().getDescription());
		// meetingRoomDTO.setLocationId(bookMeetingRoom.getLocation().getLocationId());
		// meetingRoomDTO.setMeetingRoomId(bookMeetingRoom.getMeetingRoom().getMeetingRoomId());
		// meetingRoomDTO.setName(bookMeetingRoom.getMeetingRoom().getName());

		bookMeetingRoomDTO.setMeetingRoomId(bookMeetingRoom.getMeetingRoom().getMeetingRoomId());
		return bookMeetingRoomDTO;

	}

	public static BookMeetingRoom convertDtoObjectToDao(BookMeetingRoomDTO bookMeetingRoomDTO) {
		BookMeetingRoom bookMeetingRoom = new BookMeetingRoom();
		bookMeetingRoom.setBeginTime(bookMeetingRoomDTO.getBeginTime());
		bookMeetingRoom.setBookMeetingRoomId(bookMeetingRoomDTO.getBookMeetingRoomId());

		bookMeetingRoom.setEndTime(bookMeetingRoomDTO.getEndTime());
		bookMeetingRoom.setHeadCount(bookMeetingRoomDTO.getHeadCount());
		bookMeetingRoom.setMeetingTitle(bookMeetingRoomDTO.getMeetingTitle());

		bookMeetingRoom.setEmailId(bookMeetingRoomDTO.getEmailId());

		bookMeetingRoom.setLocation(new Location(bookMeetingRoomDTO.getLocationId()));
		bookMeetingRoom.setMeetingRoom(new MeetingRoom(bookMeetingRoomDTO.getMeetingRoomId()));

		return bookMeetingRoom;

	}

	public static String getFormatedDate(String startedDate) {

		String startDate1 = null;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		try {
			Date date1 = df.parse(startedDate);
			DateFormat outputFormatter1 = new SimpleDateFormat("yyyy-MM-dd");
			startDate1 = outputFormatter1.format(date1); //
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return startDate1;
	}

	public static String getFormatedTime(String startedTime) {

		String startTime = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

		try {
			Date date1 = df.parse(startedTime);
			DateFormat outputFormatter1 = new SimpleDateFormat(" HH:mm:ss");
			startTime = outputFormatter1.format(date1); //

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return startTime;

	}

	public static String getFormatedDateAndTime(String startedDate) {

		String startDateAndTime = null;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		try {
			Date date1 = df.parse(startedDate);
			DateFormat outputFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startDateAndTime = outputFormatter1.format(date1); //

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return startDateAndTime;
	}
}
