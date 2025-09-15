package com.example.ClimateChangeBackend.dtos;

import com.example.ClimateChangeBackend.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.ClimateChangeBackend.entities.Role.ROLE_EMPLOYEE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "RUT is required")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9K]$", message = "Invalid RUT format")
    private String rut;

    @NotBlank(message = "First name is required")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "The password must be at least 8 characters long")
    private String password;
    private Role role = ROLE_EMPLOYEE;
}
