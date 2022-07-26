package com.example.vehicledetection.service;

import com.example.vehicledetection.domain.Camera;
import com.example.vehicledetection.domain.Location;
import com.example.vehicledetection.domain.Vehicle;
import com.example.vehicledetection.repository.CameraRepository;
import com.example.vehicledetection.repository.LocationRepository;
import com.example.vehicledetection.repository.VehicleRepository;
import com.example.vehicledetection.response.DetectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CameraRepository cameraRepository;
    @Autowired
    LocationRepository locationRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        List<Camera> listOfCameras = vehicle.getCameras();
        List<Camera> validCameras = new ArrayList<>();

        if (listOfCameras != null) {

            listOfCameras.forEach(c -> {
                Camera cameraFromDatabase = cameraRepository.findById(c.getId()).orElse(null);
                if (cameraFromDatabase == null) {
                    throw new ResourceNotFoundException("Camera ID not found" + c.getId());
                }
              validCameras.add(cameraFromDatabase);

            });
//            for (Camera c : listOfCameras) {
//                Camera cameraFromDatabase = cameraRepository.findById(c.getId()).orElse(null);
//                if (cameraFromDatabase == null) {
//                    throw new ResourceNotFoundException("Camera ID not found" + c.getId());
//                }
//                validCameras.add(cameraFromDatabase);
//            }
            vehicle.setCameras(validCameras);
        } else {
            throw new IllegalStateException("Camera cannot be null");

        }
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return savedVehicle;
    }

    public DetectionResponse detectVehicleMoment(Long vehicleId, Long locationId) {

        DetectionResponse detectionResponse = new DetectionResponse();
        Vehicle vehicleFromDatabase = vehicleRepository.findById(vehicleId).orElse(null);
        Location locationFromDatabase = locationRepository.findById(locationId).orElse(null);

        if (vehicleFromDatabase == null) {
            throw new ResourceNotFoundException("VehicleId is not Found" + vehicleId);
        }

        if (locationFromDatabase == null) {
            throw new ResourceNotFoundException("locationID is not Found" + locationId);
        }

        List<Camera> cameraList = vehicleFromDatabase.getCameras();
        List<Camera> detectedListOfCamera = new ArrayList<>();

        cameraList.forEach(camera -> {
            Location location = camera.getLocation();

            if (location.getId().equals(locationFromDatabase.getId())) {
                detectedListOfCamera.add(camera);
            }

        });

//        for (Camera camera : cameraList) {
//            Location location = camera.getLocation();
//
//            if (location.getId().equals(locationFromDatabase.getId())) {
//                detectedListOfCamera.add(camera);
//            }
//
//        }
        if (detectedListOfCamera.size() == 0) {

            detectionResponse.setStatus("Not found");

            return detectionResponse;
        } else {

            detectionResponse.setStatus("Found");
            detectionResponse.setCameraList(detectedListOfCamera);
            return detectionResponse;

        }

    }
}
