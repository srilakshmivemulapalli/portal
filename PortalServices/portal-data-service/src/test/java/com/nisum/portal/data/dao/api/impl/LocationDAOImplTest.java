package com.nisum.portal.data.dao.api.impl;

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

import com.nisum.portal.data.dao.impl.LocationDAOImpl;
import com.nisum.portal.data.domain.Location;
import com.nisum.portal.data.repository.LocationRepository;

@RunWith(MockitoJUnitRunner.class)
public class LocationDAOImplTest {

	@InjectMocks
	LocationDAOImpl locationDAOImpl;

	@Mock
	LocationRepository locationRepository;

	Location expected;

	@Before
	public void setUp() {
		expected = new Location();
		expected.setLocationId(1);
		expected.setLocationName("INDIA");

	}

	@Test
	public void registerLocationTest() {

		Location expected = new Location();

		when(locationRepository.save(expected)).thenReturn(expected);

		Location actual = locationDAOImpl.registerLocation(expected);

		assertEquals(expected, actual);
	}

	@Test
	public void getAllLocationTest() {
		List<Location> expectedLocation = new ArrayList<>();
		expectedLocation.add(expected);

		Location location1 = new Location();
		location1.setLocationId(2);
		location1.setLocationName("US");
		expectedLocation.add(location1);

		when(locationRepository.findAll()).thenReturn(expectedLocation);

		List<Location> actualLocation = locationDAOImpl.getAllLocation();

		assertEquals(expectedLocation, actualLocation);
	}

	@Test
	public void findByLocationIdTest() {

		int locationId = 1;

		when(locationRepository.findOne(locationId)).thenReturn(expected);

		Location actual = locationDAOImpl.findByLocationId(locationId);

		assertEquals(expected, actual);
	}	
}
