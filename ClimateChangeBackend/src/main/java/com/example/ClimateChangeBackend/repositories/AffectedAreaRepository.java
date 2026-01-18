package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.AffectedAreaDTO;
import com.example.ClimateChangeBackend.dtos.InvalidGeometryDTO;
import com.example.ClimateChangeBackend.dtos.MeasurePointAreaResponse;
import com.example.ClimateChangeBackend.entities.AffectedAreaEntity;

import java.util.List;
import java.util.Optional;

public interface AffectedAreaRepository {
    List<AffectedAreaEntity> findAll();
    Optional<AffectedAreaEntity> findById(Long idArea);
    AffectedAreaEntity save(AffectedAreaEntity area);
    int deleteById(Long idArea);
    List<MeasurePointAreaResponse> findMeasurePointsInRiskAreas();
    List<InvalidGeometryDTO> findInvalidGeometry();
    List<AffectedAreaDTO> findValidAffectedAreas();
}

