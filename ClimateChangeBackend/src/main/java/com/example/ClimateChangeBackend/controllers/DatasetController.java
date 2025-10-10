package com.example.ClimateChangeBackend.controllers;

import com.example.ClimateChangeBackend.dtos.DatasetRequest;
import com.example.ClimateChangeBackend.dtos.MessageResponse;
import com.example.ClimateChangeBackend.entities.DatasetEntity;
import com.example.ClimateChangeBackend.security.JwtUtil;
import com.example.ClimateChangeBackend.security.services.RefreshTokenService;
import com.example.ClimateChangeBackend.services.Datasetservice;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/dataset")
public class DatasetController {

    private AuthenticationManager authenticationManager;
    private Datasetservice datasetService;
    private RefreshTokenService refreshTokenService;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtTokenUtil;

    @PostMapping("/create-dataset")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> createDataset(@Valid @RequestBody DatasetRequest datasetRequest) {
        datasetService.createDataset(datasetRequest);
        return ResponseEntity.ok().body(new MessageResponse("Dataset created successfully",true));
    }

    @GetMapping("/get-by-id/{idDataset}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getById(@PathVariable Long idDataset) {
        DatasetEntity dataset = datasetService.getDatasetById(idDataset);
        return ResponseEntity.ok().body(dataset);
    }

    @GetMapping("/get-by-name/{nameDataset}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getByName(@PathVariable String nameDataset) {
        DatasetEntity dataset = datasetService.getDatasetByName(nameDataset);
        return ResponseEntity.ok().body(dataset);
    }

}


