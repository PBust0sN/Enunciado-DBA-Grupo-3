package com.example.ClimateChangeBackend.controllers;

import com.example.ClimateChangeBackend.dtos.MeasurePointRequest;
import com.example.ClimateChangeBackend.entities.MeasurePointsEntity;
import com.example.ClimateChangeBackend.services.MeasurePointsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/v1/measurePoints")
public class MeasurePointsController {
    private MeasurePointsService  measurePointsService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<MeasurePointsEntity>> findById(@PathVariable("id") Long id){
        Optional<MeasurePointsEntity>  measurePointsEntity = measurePointsService.findById(id);
        return ResponseEntity.ok().body(measurePointsEntity);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<MeasurePointsEntity>> getAll(){
        List<MeasurePointsEntity> measurePointsEntities = measurePointsService.findAll();
        return ResponseEntity.ok().body(measurePointsEntities);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MeasurePointsEntity> create(@Valid @RequestBody MeasurePointRequest measurePointRequest){
        MeasurePointsEntity measurePointsEntity = measurePointsService.save(measurePointRequest);
        return ResponseEntity.ok().body(measurePointsEntity);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody MeasurePointRequest measurePointRequest){
        //IF 1 THEN IS SUCCESFULL
        if(measurePointsService.update(measurePointRequest)==1){
            return ResponseEntity.ok().body(measurePointRequest);
        }
        return ResponseEntity.notFound().build();
    }
}
