package com.example.ClimateChangeBackend.dtos;

import lombok.Data;

@Data
public class MeasurePointDTO {
    private Long id;
    private double latitud;
    private double longitud;
    private String geom;
    private String sensorType;
}
