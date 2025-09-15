package com.example.ClimateChangeBackend.security.services;


import com.example.ClimateChangeBackend.entities.UserEntity;
import com.example.ClimateChangeBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByRut(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with rut: " + username);
        }
        return CustomUserDetails.build(user.get());
    }
}
