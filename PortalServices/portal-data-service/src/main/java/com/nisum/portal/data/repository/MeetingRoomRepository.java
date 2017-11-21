package com.nisum.portal.data.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.nisum.portal.data.domain.MeetingRoom;



/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer>{

	List<MeetingRoom> findAll();
	
	@Query(value = "SELECT m from  MeetingRoom m where m.meetingRoomId IN(SELECT bm.meetingRoom.meetingRoomId from BookMeetingRoom bm where bm.location.locationId=:locationId and bm.beginTime>:startDate)" )
	List<MeetingRoom> findAllByLocationIdAndDate(@Param("locationId") int locationId,@Param("startDate") Timestamp startDate);
	
	@Query(value = "select mr from  MeetingRoom mr, Location l where l.locationId = mr.location and l.locationId=:locationId and mr.meetingRoomId NOT IN(select res.meetingRoom.meetingRoomId from BookMeetingRoom as res  where ((:beginTime BETWEEN res.beginTime AND res.endTime) OR  (:endTime BETWEEN res.beginTime AND res.endTime))))")
	public List<MeetingRoom> getAvailableMeetingRoom(@Param("locationId") int locationId, @Param("beginTime") Timestamp beginTime, @Param("endTime") Timestamp endTime);
	@Query(value = "SELECT m from  MeetingRoom m where m.meetingRoomId IN(SELECT bm.meetingRoom.meetingRoomId from BookMeetingRoom bm where bm.beginTime>:startDate)" )
	public List<MeetingRoom> findAllByDate(@Param("startDate") Timestamp startDate);
	
	

	


}