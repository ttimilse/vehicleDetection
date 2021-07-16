package com.example.vehicledetection.service;

import com.example.vehicledetection.domain.Location;
import com.example.vehicledetection.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public Location saveLocation(Location location) {
        Location savedLocation = locationRepository.save(location);
        return savedLocation;
    }

    public List<Location> getAllLocation() {
        List<Location> locationList = locationRepository.findAll();
        return locationList;
    }

    public Location updateLocation(Location location, Long id) {
        Location locationToUpdate = locationRepository.findById(id).orElse(null);
        if (locationToUpdate == null) {
            throw new ResourceNotFoundException("ID" + id + "Not found");
        }
        location.setId(id);
        Location updatedLocation = locationRepository.save(location);
        return updatedLocation;
    }


    public void deleteLocation(Long id) {
        Location locationToDelete = locationRepository.findById(id).orElse(null);
        if (locationToDelete == null) {
            throw new ResourceNotFoundException("ID" + id + "Not found");
        }
        locationRepository.delete(locationToDelete);


    }

    public Location locationByID(Long id) {

        Location location = locationRepository.findById(id).orElse(null);
        if (location == null) {
            throw new ResourceNotFoundException("ID" + id + "Not found");
        }
        return locationRepository.getById(id);

    }

}
