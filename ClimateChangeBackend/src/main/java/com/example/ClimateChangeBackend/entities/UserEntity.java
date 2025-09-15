package com.example.ClimateChangeBackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String rut;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String refreshToken;
    private Long refreshTokenExpiration;
}