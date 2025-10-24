package com.example.ClimateChangeBackend.controllers;

import com.example.ClimateChangeBackend.dtos.AnomaliaDTO;
import com.example.ClimateChangeBackend.entities.MeasurementEntity;
import com.example.ClimateChangeBackend.services.MeasurementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/v1/measurements")
@CrossOrigin("*")
public class MeasurementController {

    private MeasurementService measurementService;

    @GetMapping("/get-measurement/{id}")
    public ResponseEntity<MeasurementEntity> getMeasurementById(@PathVariable long id) {
        Optional<MeasurementEntity> measurement = measurementService.getMeasurementById(id);
        return measurement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/extreme")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public List<Map<String, Object>> extremeEventDetection() {
        return measurementService.extremeEventDetection();
    }

    @PutMapping("/update-measurement/{id}")
    public ResponseEntity<MeasurementEntity> updateMeasurement(@PathVariable long id, @Valid @RequestBody MeasurementEntity measurementDetails) {
        Optional<MeasurementEntity> existingMeasurement = measurementService.getMeasurementById(id);
        if (existingMeasurement.isPresent()) {
            MeasurementEntity measurementToUpdate = existingMeasurement.get();
            measurementToUpdate.setValue_measurement(measurementDetails.getValue_measurement());
            measurementToUpdate.setDate_measurement(measurementDetails.getDate_measurement());
            measurementToUpdate.setId_points_measurement(measurementDetails.getId_points_measurement());
            measurementToUpdate.setId_dataset(measurementDetails.getId_dataset());
            Optional<MeasurementEntity> updatedMeasurement = measurementService.updateMeasurement(measurementToUpdate);
            return updatedMeasurement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-measurement/{id}")
    public ResponseEntity<Void> deleteMeasurement(@PathVariable long id) {
        if (measurementService.checkMeasurementExists(id)) {
            measurementService.deleteMeasurementById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/calculate-anomalia")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<AnomaliaDTO>> calculateAnomalia(){
        List<AnomaliaDTO> anomaliaDTO = measurementService.tempetureAnomalyCalculation();
        if(anomaliaDTO == null || anomaliaDTO.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(anomaliaDTO);
    }
}
