package com.example.vehicledetection.repository;

import com.example.vehicledetection.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
