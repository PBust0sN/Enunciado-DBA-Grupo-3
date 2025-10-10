package com.example.ClimateChangeBackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurePointRequest {

    @NotBlank(message = "Latitud is required")
    @JsonProperty("latitud")
    private Long latitud;

    @NotBlank(message = "Longitud is required")
    @JsonProperty("longitud")
    private Long longitud;

    @NotBlank(message = "Sensor type is required")
    @JsonProperty("sensor_type")
    private String sensorType;
}
