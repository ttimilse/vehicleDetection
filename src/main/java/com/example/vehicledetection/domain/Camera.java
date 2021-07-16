package com.example.vehicledetection.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Camera extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    Location location;
    @ManyToMany
    List<Vehicle> vehicleList;
    private String model;
    private String make;
    private String type;
}
