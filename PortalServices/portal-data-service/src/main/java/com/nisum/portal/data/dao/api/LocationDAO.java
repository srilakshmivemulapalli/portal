package com.nisum.portal.data.dao.api;

import java.util.List;
import com.nisum.portal.data.domain.Location;





public interface LocationDAO {
	
	public Location  registerLocation(Location location);
	public List<Location> getAllLocation();
	public Location findByLocationId(int locationId);
	

}


