package com.example.ClimateChangeBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterpolarDatosSemDTO {

    private Long idMeasurePoints;
    private Date weekStart;
    private Date weekEnd;
    private double avgValue;

}
