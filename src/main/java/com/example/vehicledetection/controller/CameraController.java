package com.example.vehicledetection.controller;

import com.example.vehicledetection.domain.Camera;
import com.example.vehicledetection.domain.Location;
import com.example.vehicledetection.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@RequestMapping("/cameras")
public class CameraController {

    @Autowired
    CameraService cameraService;

    @PostMapping
    public ResponseEntity saveCamera(@RequestBody Camera camera) {
        Camera savedCamera = cameraService.saveCamera(camera);
        return ResponseEntity.ok(savedCamera);

    }

    @GetMapping
    public ResponseEntity getAllCamera() {
        List<Camera> cameraList = cameraService.getAllCamera();
        return new ResponseEntity(cameraList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCameras(@RequestBody Camera camera, @PathVariable Long id) {
        cameraService.updateCamera(camera, id);
        return new ResponseEntity("Camera Updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCamera(Long id) {
        cameraService.deleteCamera(id);
        return new ResponseEntity("Camera Deleted",HttpStatus.OK);
    }
}
