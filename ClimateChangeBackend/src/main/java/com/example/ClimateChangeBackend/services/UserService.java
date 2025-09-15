package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.RegisterRequest;
import com.example.ClimateChangeBackend.entities.Role;
import com.example.ClimateChangeBackend.entities.UserEntity;
import com.example.ClimateChangeBackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public boolean checkUserExists(String rut, String email){
        return userRepository.existsByEmail(email) || userRepository.existsByRut(rut);
    }

    public Optional<UserEntity> getUserByRut(String rut){
        return userRepository.findByRut(rut);
    }

    public void createUser(RegisterRequest registerRequest) {
        UserEntity userEntity = UserEntity.builder()
                .rut(registerRequest.getRut())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .role(Role.ROLE_EMPLOYEE)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(userEntity);
    }

    public void updateRefreshToken(Long id, String refreshToken, long refreshExpiryTime) {
        userRepository.updateRefreshToken(id, refreshToken, refreshExpiryTime);
    }

    public void clearRefreshToken(Long idUser){
        userRepository.clearRefreshToken(idUser);
    }
}