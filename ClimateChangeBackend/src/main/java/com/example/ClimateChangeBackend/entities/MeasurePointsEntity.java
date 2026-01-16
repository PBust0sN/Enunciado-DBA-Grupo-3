package com.example.ClimateChangeBackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.locationtech.jts.geom.Point;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurePointsEntity {

    private Long idMeasurePoints;
    private double latitud;
    private double longitud;
    private Point geom;
    private String sensorType;
}
