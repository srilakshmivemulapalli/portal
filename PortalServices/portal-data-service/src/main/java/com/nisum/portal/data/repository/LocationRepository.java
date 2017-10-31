package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.domain.MeetingRoom;


/**
 * @author Raunak
 *
 */
@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Integer>{
	

}
