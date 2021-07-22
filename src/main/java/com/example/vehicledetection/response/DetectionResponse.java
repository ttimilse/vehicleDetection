package com.example.vehicledetection.response;

import com.example.vehicledetection.domain.Camera;
import lombok.Data;

import java.util.List;

@Data
public class DetectionResponse {
    String status;
    List<Camera> cameraList;
}
