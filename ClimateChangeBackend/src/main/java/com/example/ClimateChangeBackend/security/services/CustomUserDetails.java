package com.example.ClimateChangeBackend.security.services;



import com.example.ClimateChangeBackend.entities.Role;
import com.example.ClimateChangeBackend.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {
    @Serial
    private static final long  serialVersionUID = 1L;

    @Getter
    private long id;

    @Getter
    private String email;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @JsonIgnore
    private String password;

    @Getter
    private String rut;

    @Getter
    private Role role;

    private Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetails build(UserEntity user) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().name())
        );

        return CustomUserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .rut(user.getRut())
                .role(user.getRole())
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return rut;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
