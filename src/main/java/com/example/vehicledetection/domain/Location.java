package com.example.vehicledetection.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Location extends BaseModel {
    @OneToMany(mappedBy = "location")
    @JsonIgnore
    List<Camera> cameraLists;
    private String streetName;
    private String cityName;

}
