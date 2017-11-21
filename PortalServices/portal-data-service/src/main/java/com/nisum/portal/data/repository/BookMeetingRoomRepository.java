package com.nisum.portal.data.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.BookMeetingRoom;
import com.nisum.portal.data.domain.MeetingRoom;

/**
 * 
 * @author nisum
 *
 */

public interface BookMeetingRoomRepository extends JpaRepository<BookMeetingRoom, Integer> {

	// public List<Holiday> findByYear(String year);

	public BookMeetingRoom findByBookMeetingRoomId(int bookMeetingRoomId);

	@Query(value = "SELECT bm from BookMeetingRoom bm where bm.emailId = :emailId")
	public List<BookMeetingRoom> getUserBooking(@Param("emailId") String emailId);

	@Query(value = "SELECT bm from BookMeetingRoom bm where (:beginTime BETWEEN bm.beginTime AND bm.endTime) OR  (:endTime BETWEEN bm.beginTime AND bm.endTime)")
	public BookMeetingRoom getMeetingRoomForTimePeriod(@Param("beginTime") Timestamp beginTime,
			@Param("endTime") Timestamp endTime);
	
	
	@Query(value = "select bm from BookMeetingRoom bm where bm.beginTime > :startDate" )
	public List<BookMeetingRoom> findAllByDate(@Param("startDate") Timestamp startDate);
	
	@Query(value = "select bmr from BookMeetingRoom bmr, MeetingRoom mr, Location l where bmr.beginTime > :startDate and mr.location = l.locationId and  l.locationId = :locationId" )
	List<BookMeetingRoom> findAllByLocationIdAndDate(@Param("locationId") int locationId,@Param("startDate") Timestamp startDate);

}
