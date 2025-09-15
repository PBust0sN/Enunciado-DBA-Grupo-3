package com.example.ClimateChangeBackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "RUT is required")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9K]$", message = "Invalid RUT format")
    private String rut;
    @NotBlank(message = "Password is required")
    private String password;
}
