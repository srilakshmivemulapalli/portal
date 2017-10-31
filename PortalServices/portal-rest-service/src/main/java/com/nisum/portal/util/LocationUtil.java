package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.service.dto.LocationDTO;

public class LocationUtil {
	public static List<LocationDTO> convertDaoListToDto(List<Location> location) {
		List<LocationDTO> locationDTOList = new ArrayList<LocationDTO>();
		
		if (CollectionUtils.isNotEmpty(location)) {
			for(Location locationDAO : location){
				LocationDTO locationDTO = new LocationDTO();
				
				locationDTO.setLocationId(locationDAO.getLocationId());
				locationDTO.setName(locationDAO.getName());
				
				
				locationDTOList.add(locationDTO);
				
			}
		}
		return locationDTOList;
	}
	
	public static Location convertDtoToDao(LocationDTO locationDTO){
		
		Location locationDAO = new Location();
		locationDAO.setLocationId(locationDTO.getLocationId());
		locationDAO.setName(locationDTO.getName());
		
		return locationDAO;
		
	}

}
