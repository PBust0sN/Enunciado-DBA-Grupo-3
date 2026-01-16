package com.example.ClimateChangeBackend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AffectedAreaResponse {
    private Long idArea;
    private String name;
    private String areaType;
    private String wkt; // geom en WKT
}
