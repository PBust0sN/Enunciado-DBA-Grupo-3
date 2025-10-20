package com.example.ClimateChangeBackend.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class PointWithoutGeorefDTO {
    private Long idMeasurePoints;
    private OffsetDateTime lastMeasurementDate;
}
