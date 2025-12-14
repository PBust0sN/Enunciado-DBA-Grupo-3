package com.example.ClimateChangeBackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurePointsEntity {

    private Long idMeasurePoints;
    private double latitud;
    private double longitud;
    private String sensorType;
}
