package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.AnomaliaDTO;
import com.example.ClimateChangeBackend.entities.MeasurementEntity;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepository {
    Optional<MeasurementEntity> findById(long id);
    MeasurementEntity save(MeasurementEntity measurementEntity);
    int deleteById(long id);
    List<AnomaliaDTO> tempetureAnomalyCalculation();
}
