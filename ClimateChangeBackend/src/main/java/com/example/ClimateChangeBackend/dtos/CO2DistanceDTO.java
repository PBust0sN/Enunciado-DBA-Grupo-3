package com.example.ClimateChangeBackend.dtos;

import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
public class CO2DistanceDTO {

    private Long co2_point_id;
    private Long temp_point_id;
    private double latitud;
    private double longitud;
    private String sensorType;
    private String co2_geom;
    private double distance_meters;
}
