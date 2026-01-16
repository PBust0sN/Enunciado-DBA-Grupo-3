package com.example.ClimateChangeBackend.dtos;

import java.awt.*;

public class InvalidPointDTO {
    private Long id;
    private String wkt;

    public InvalidPointDTO() {
    }

    public InvalidPointDTO(Long id, String wkt) {
        this.id = id;
        this.wkt = wkt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }
}
