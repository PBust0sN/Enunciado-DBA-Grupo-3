package com.example.ClimateChangeBackend.dtos;


import lombok.Data;

@Data
public class AffectedAreaDTO {
    private Long idArea;
    private String name;
    private String areaType;
    private String wkt;
}
