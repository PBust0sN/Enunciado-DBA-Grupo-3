package com.example.ClimateChangeBackend.dtos;
import lombok.Data;

@Data
public class AffectedAreaRequest {
    private String name;
    private String areaType;
    private String wkt; // Ej: "POLYGON((-74 4, -74 5, -73 5, -73 4, -74 4))"
}