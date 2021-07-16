package com.example.vehicledetection.controller;

import com.example.vehicledetection.domain.Location;
import com.example.vehicledetection.repository.LocationRepository;
import com.example.vehicledetection.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping
    public ResponseEntity saveLocation(@RequestBody Location location) {
        Location savedLocation = locationService.saveLocation(location);
        return ResponseEntity.ok(savedLocation);

    }

    @GetMapping
    public ResponseEntity getAllLocation() {
        List<Location> locationList = locationService.getAllLocation();
        return new ResponseEntity(locationList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLocations(@RequestBody Location location, @PathVariable Long id) {
        Location updatedLocation = locationService.updateLocation(location, id);
        return new ResponseEntity(updatedLocation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLocation(Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity("Located Deleted",HttpStatus.OK);
    }


}
