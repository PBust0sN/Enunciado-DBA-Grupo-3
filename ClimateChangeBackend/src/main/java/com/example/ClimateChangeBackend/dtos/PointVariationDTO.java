package com.example.ClimateChangeBackend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointVariationDTO {
    private Long idMeasurePoints;
    private Double temperatureStddev;
}
