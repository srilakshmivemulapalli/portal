package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.service.dto.LocationDTO;

public interface LocationService {
	
	public String  registerLocation(LocationDTO location);
	
	public List<LocationDTO> getAllLocation();
	
	}
