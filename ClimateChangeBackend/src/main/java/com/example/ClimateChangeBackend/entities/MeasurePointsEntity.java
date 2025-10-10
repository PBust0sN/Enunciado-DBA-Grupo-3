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
    private Long latitud;
    private Long longitud;
    private String sensorType;
}
