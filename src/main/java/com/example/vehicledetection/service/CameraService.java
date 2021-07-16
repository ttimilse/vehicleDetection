package com.example.vehicledetection.service;

import com.example.vehicledetection.domain.Camera;
import com.example.vehicledetection.domain.Location;
import com.example.vehicledetection.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {
    @Autowired
    CameraRepository cameraRepository;
    @Autowired
    LocationService locationService;

    public Camera saveCamera(Camera camera) {
        Long locationID = camera.getLocation().getId();

        if (locationID == null) {
            throw new ResourceNotFoundException("Location Id not found." + locationID);
        }
        Location location = locationService.locationByID(locationID);
        camera.setLocation(location);
        Camera savedCamera = cameraRepository.save(camera);
        return savedCamera;
    }

    public List<Camera> getAllCamera() {
        List<Camera> cameraList = cameraRepository.findAll();
        return cameraList;
    }

    public void updateCamera(Camera camera, Long id) {
        Camera cameraToUpdate = cameraRepository.findById(id).orElse(null);
        if (cameraToUpdate == null) {
            throw new ResourceNotFoundException("ID" + id + "Not found");
        }
        camera.setId(id);
        cameraRepository.save(camera);
    }

    public void deleteCamera(Long id) {
        Camera cameraToDelete = cameraRepository.findById(id).orElse(null);
        if (cameraToDelete == null) {
            throw new ResourceNotFoundException("ID" + id + "Not found");
        }
        cameraRepository.delete(cameraToDelete);


    }
}
