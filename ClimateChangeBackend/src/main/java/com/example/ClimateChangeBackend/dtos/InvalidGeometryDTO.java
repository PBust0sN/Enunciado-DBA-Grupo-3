package com.example.ClimateChangeBackend.dtos;

import lombok.Data;

@Data
public class InvalidGeometryDTO {
    private Long id;
    private String wkt;
    public String motivo;
}
