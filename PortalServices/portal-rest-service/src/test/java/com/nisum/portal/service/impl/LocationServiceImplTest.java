package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.api.LocationDAO;
import com.nisum.portal.data.dao.api.impl.LocationDAOImplTest;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.service.dto.LocationDTO;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {

	@InjectMocks
	LocationImpl locationServiceImpl;

	@Mock
	LocationDAO locationDAOImpl;

	LocationDTO locationDTO;

	Location location;

	@Before
	public void setUp() {
		locationDTO = new LocationDTO();
		locationDTO.setLocationId(1);
		locationDTO.setLocationName("INDIA");

		location = new Location();
		location.setLocationId(1);
		location.setLocationName("INDIA");

	}

	@Test
	public void registerLocationSaveTest() {

		String expected = "Saved Successfully...";

		when(locationDAOImpl.findByLocationId(location.getLocationId())).thenReturn(null);
		when(locationDAOImpl.registerLocation(location)).thenReturn(location);

		String actual = locationServiceImpl.registerLocation(locationDTO);

		assertEquals(expected, actual);

	}

	@Test
	public void registerLocationUpdateTest() {

		String expected = "Updated Successfully...";

		when(locationDAOImpl.findByLocationId(location.getLocationId())).thenReturn(location);
		when(locationDAOImpl.registerLocation(location)).thenReturn(location);

		String actual = locationServiceImpl.registerLocation(locationDTO);

		assertEquals(expected, actual);

	}

	@Test
	public void getAllLocationTest() {

		List<LocationDTO> expectedLocationList = new ArrayList<>();

		expectedLocationList.add(locationDTO);

		LocationDTO locationDTO1 = new LocationDTO();

		locationDTO1.setLocationId(2);
		locationDTO1.setLocationName("US");

		expectedLocationList.add(locationDTO1);

		List<Location> locationList = new ArrayList<>();

		locationList.add(location);

		Location location1 = new Location();
		location1.setLocationId(2);
		location1.setLocationName("US");
		locationList.add(location1);

		when(locationDAOImpl.getAllLocation()).thenReturn(locationList);

		List<LocationDTO> actualLocationList = locationServiceImpl.getAllLocation();

		assertEquals(expectedLocationList, actualLocationList);
	}	
}
