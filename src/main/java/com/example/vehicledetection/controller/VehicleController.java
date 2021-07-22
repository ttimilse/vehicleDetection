package com.example.vehicledetection.controller;

import com.example.vehicledetection.domain.Vehicle;
import com.example.vehicledetection.response.DetectionResponse;
import com.example.vehicledetection.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @PostMapping
    public ResponseEntity saveVehicles(@RequestBody Vehicle vehicle){

        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    @GetMapping("/{vehicleId}/location/{locationId}")
    public ResponseEntity detectVehicle(@PathVariable Long vehicleId, @PathVariable Long locationId){
        DetectionResponse result = vehicleService.detectVehicleMoment(vehicleId,locationId);
        return ResponseEntity.ok(result);


    }

}
