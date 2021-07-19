package com.example.vehicledetection.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Vehicle extends BaseModel{
    @ManyToMany
    List<Camera> cameras;
    private String name;
    private String make;
    private String model;
    private String licensePlate;

}
