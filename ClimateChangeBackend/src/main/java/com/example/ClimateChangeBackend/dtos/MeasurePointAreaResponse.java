package com.example.ClimateChangeBackend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasurePointAreaResponse {
    private Long idMeasurePoints;
    private Double latitud;
    private Double longitud;
    private String sensorType;
    private Long idArea;
    private String areaName;
}
