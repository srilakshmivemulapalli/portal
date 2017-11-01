package com.nisum.portal.data.repository;


	import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.BookMeetingRoom;
	

	/**
	 * 
	 * @author nisum
	 *
	 */
	 
	 
	
	public interface BookMeetingRoomRepository extends JpaRepository<BookMeetingRoom, Integer>{
		
		//public List<Holiday> findByYear(String year);
		
		public BookMeetingRoom findByBookMeetingRoomId(int bookMeetingRoomId);
		
		@Query(value = "SELECT bm from BookMeetingRoom bm where bm.emailId = :emailId")
		public List<BookMeetingRoom> getUserBooking(@Param("emailId") String emailId);
		
		@Query(value = "SELECT bm from BookMeetingRoom bm where bm.beginTime =:beginTime and bm.endTime =:endTime")
		public BookMeetingRoom getMeetingRoomForTimePeriod(@Param("beginTime") Timestamp beginTime, @Param("endTime") Timestamp endTime);
		
	}



