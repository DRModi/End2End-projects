package com.drmodi.locationweb.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drmodi.locationweb.entities.Location;

@Service
public interface LocationService {

	Location saveLocation(Location location);

	Location updateLocation(Location location);

	void deleteLocation(Location location);

	Location getLocationById(int id);

	List<Location> getAllLocation();

}
