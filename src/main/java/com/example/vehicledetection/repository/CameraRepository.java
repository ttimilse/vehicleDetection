package com.example.vehicledetection.repository;

import com.example.vehicledetection.domain.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera,Long> {
}
