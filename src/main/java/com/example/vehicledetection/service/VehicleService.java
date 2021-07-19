package com.example.vehicledetection.service;

import com.example.vehicledetection.domain.Camera;
import com.example.vehicledetection.domain.Vehicle;
import com.example.vehicledetection.repository.CameraRepository;
import com.example.vehicledetection.repository.LocationRepository;
import com.example.vehicledetection.repository.VehicleRepository;
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

    public Vehicle saveVehicle(Vehicle vehicle){
        List<Camera> listOfCameras = vehicle.getCameras();
        List<Camera> validCameras = new ArrayList<>();

        if(listOfCameras != null) {
            for (Camera c : listOfCameras) {
                Camera cameraFromDatabase = cameraRepository.getById(c.getId());
                if (cameraFromDatabase == null) {
                    throw new ResourceNotFoundException("Camera ID not found" + c.getId());
                }
                validCameras.add(cameraFromDatabase);
            }
            vehicle.setCameras(validCameras);
        }
        else{
            throw new IllegalStateException("Camera cannot be null");

        }


        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return savedVehicle;
    }

}
