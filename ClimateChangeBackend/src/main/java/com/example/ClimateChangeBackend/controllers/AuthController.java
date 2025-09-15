package com.example.ClimateChangeBackend.controllers;


import com.example.ClimateChangeBackend.dtos.LoginRequest;
import com.example.ClimateChangeBackend.dtos.MessageResponse;
import com.example.ClimateChangeBackend.dtos.RegisterRequest;
import com.example.ClimateChangeBackend.dtos.TokenRefreshResponse;
import com.example.ClimateChangeBackend.entities.UserEntity;
import com.example.ClimateChangeBackend.security.JwtUtil;
import com.example.ClimateChangeBackend.security.services.CustomUserDetails;
import com.example.ClimateChangeBackend.security.services.RefreshTokenService;
import com.example.ClimateChangeBackend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtil jwtTokenUtil;
    private RefreshTokenService refreshTokenService;
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.checkUserExists(registerRequest.getRut(), registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User already exists", false));
        }

        userService.createUser(registerRequest);
        return ResponseEntity.ok().body(new MessageResponse("User account created successfully", true));
    }

    @PostMapping("/admin/create-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Example for role-based access control
    public ResponseEntity<?> createUserByAdmin(@Valid @RequestBody RegisterRequest registerRequest){
        if(userService.checkUserExists(registerRequest.getRut(), registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User already exists", false));
        }
        userService.createUser(registerRequest);
        return ResponseEntity.ok().body(new MessageResponse("User account created by admin successfully", true));

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Optional<UserEntity> user = userService.getUserByRut(loginRequest.getRut());

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse("Error: User not found", false));
            }


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.get().getRut(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenUtil.generateJwtToken(authentication);

            ResponseCookie jwtCookie = ResponseCookie.from("SESSION", jwt)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(Duration.ofHours(1))
                    .sameSite("Lax")
                    .build();

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String refreshToken = refreshTokenService.createRefreshToken(customUserDetails.getId());
            ResponseCookie refreshCookie = ResponseCookie.from("REFRESH", refreshToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/api/v1/auth/refreshtoken")
                    .maxAge(Duration.ofDays(7))
                    .sameSite("Lax")
                    .build();

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("id", customUserDetails.getId());
            responseBody.put("rut", customUserDetails.getRut());
            responseBody.put("first_name", customUserDetails.getFirstName());
            responseBody.put("last_name", customUserDetails.getLastName());
            responseBody.put("role", customUserDetails.getRole());
            responseBody.put("access_token", jwt);
            responseBody.put("refresh_token", refreshToken);


            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body(responseBody);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Error: Invalid password", false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error during authentication: " + e.getMessage(), false));
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@CookieValue(name = "REFRESH", required = false) String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Error: Refresh token cookie not found", false));
        }
        return refreshTokenService.findByToken(refreshToken).map(
                userEntity -> {
                    try {
                        refreshTokenService.verifyExpiration(userEntity);
                        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(userEntity.getRut());
                        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        String jwt = jwtTokenUtil.generateJwtToken(authentication);
                        String newRefreshToken = refreshTokenService.createRefreshToken(userEntity.getId());

                        ResponseCookie jwtCookie = ResponseCookie.from("SESSION", jwt)
                                .httpOnly(true)
                                .secure(false)
                                .path("/")
                                .maxAge(Duration.ofHours(1))
                                .sameSite("Lax")
                                .build();

                        ResponseCookie refreshCookie = ResponseCookie.from("REFRESH", newRefreshToken)
                                .httpOnly(true)
                                .secure(false)
                                .path("/api/v1/auth/refreshtoken")
                                .maxAge(Duration.ofDays(7))
                                .sameSite("Lax")
                                .build();

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                                .body(new TokenRefreshResponse(jwt, newRefreshToken, "Bearer"));
                    } catch (RuntimeException e) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new MessageResponse("Error: " + e.getMessage(), false));
                    }
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Error: Refresh token not found", false)));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        CustomUserDetails customUser = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = customUser.getId();
        refreshTokenService.deleteByUserId(userId);

        ResponseCookie deleteJwtCookie = ResponseCookie.from("SESSION","")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        ResponseCookie deleteRefreshCookie = ResponseCookie.from("REFRESH","")
                .httpOnly(true)
                .secure(true)
                .path("/api/v1/auth/refreshtoken")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,deleteJwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE,deleteRefreshCookie.toString())
                .body(new MessageResponse("Log out", true));
    }
}
