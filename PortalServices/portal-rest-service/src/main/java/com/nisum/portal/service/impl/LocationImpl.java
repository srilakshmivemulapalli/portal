package com.nisum.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.LocationDAO;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.service.api.LocationService;
import com.nisum.portal.service.dto.LocationDTO;
import com.nisum.portal.util.LocationUtil;

@Service
public class LocationImpl implements LocationService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LocationDAO locationDAO;
	
	
	
	public String registerLocation(LocationDTO location){
		logger.info("In registerLocation()..Service...");
		
		Location locationDao = LocationUtil.convertDtoToDao(location);
			Location tempLocation = locationDAO.findByLocationId(location.getLocationId());
			if(tempLocation == null){
				locationDAO.registerLocation(locationDao);	
			return	"Saved Successfully...";
			}else{
				tempLocation.setName(location.getName());
				tempLocation.setDescription(location.getDescription());
			    locationDAO.registerLocation(tempLocation);
				
			    return "Updated Successfully...";
			}
	}
	
	public  List<LocationDTO> getAllLocation(){
		logger.info("In service layer....getAllLocation()....");
		List<Location> locationList = locationDAO.getAllLocation();
		 return LocationUtil.convertDaoListToDto(locationList);
		
		
	}
	

}
