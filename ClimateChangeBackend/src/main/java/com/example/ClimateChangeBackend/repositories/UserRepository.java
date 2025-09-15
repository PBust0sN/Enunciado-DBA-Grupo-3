package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByRefreshToken(String refreshToken);
    Optional<UserEntity> findByRut(String rut);
    boolean existsByEmail(String email);
    boolean existsByRut(String rut);
    UserEntity save(UserEntity userEntity);
    int updateRefreshToken(Long userId, String refreshToken, Long expiration);
    int clearRefreshToken(Long userId);
}