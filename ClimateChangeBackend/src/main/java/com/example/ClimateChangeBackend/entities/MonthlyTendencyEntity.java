package com.example.ClimateChangeBackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyTendencyEntity {
    private String sensorType;
    private Integer year;
    private Integer month;
    private Double average;

}
