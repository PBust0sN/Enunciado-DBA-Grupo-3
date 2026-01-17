package com.example.ClimateChangeBackend.dtos;

import lombok.Data;

import java.awt.*;
@Data
public class InvalidPointDTO {
    private Long id;
    private String wkt;

    public InvalidPointDTO() {
    }

    public InvalidPointDTO(Long id, String wkt) {
        this.id = id;
        this.wkt = wkt;
    }

}
