package com.nisum.portal.data.dao.impl;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.LocationDAO;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.repository.LocationRepository;


@Configuration
public class LocationDAOImpl implements LocationDAO {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	LocationRepository locationRepository;
	
	public Location registerLocation(Location location){
		logger.info("In registerLocation()..Service...");
		return locationRepository.save(location);
			
	}
	
	@Override
	public  List<Location> getAllLocation(){
		logger.info("In service layer....getAllLocation()....");
		return locationRepository.findAll();
		
		
	}

	@Override
	public Location findByLocationId(int locationId) {
		// TODO Auto-generated method stub
		return locationRepository.findOne(locationId);
		
	}

}
