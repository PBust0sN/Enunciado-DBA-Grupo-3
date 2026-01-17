package com.example.ClimateChangeBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyTendencyDTO {
    private String sensorType;
    private Integer year;
    private Integer month;
    private Double average;

}
