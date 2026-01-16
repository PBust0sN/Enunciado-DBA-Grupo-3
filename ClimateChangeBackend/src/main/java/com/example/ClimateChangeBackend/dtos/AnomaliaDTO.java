package com.example.ClimateChangeBackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnomaliaDTO {

    private Long id_measure_points;
    private Double anomalia;
}
