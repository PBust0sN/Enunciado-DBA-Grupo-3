package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.MeasurePointAreaResponse;
import com.example.ClimateChangeBackend.entities.AffectedAreaEntity;
import com.example.ClimateChangeBackend.repositories.AffectedAreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AffectedAreaService {

    private final AffectedAreaRepository affectedAreaRepository;

    public AffectedAreaEntity save(AffectedAreaEntity area) {
        try {
            return affectedAreaRepository.save(area);
        } catch (Exception e) {
            throw new RuntimeException("Error saving affected area: " + e.getMessage());
        }
    }

    public List<AffectedAreaEntity> findAll() {
        try {
            return affectedAreaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving affected areas: " + e.getMessage());
        }
    }

    public AffectedAreaEntity findById(Long idArea) {
        try {
            return affectedAreaRepository.findById(idArea).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving affected area by ID: " + e.getMessage());
        }
    }

    public int deleteById(Long idArea) {
        try {
            return affectedAreaRepository.deleteById(idArea);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting affected area by ID: " + e.getMessage());
        }
    }

    public List<MeasurePointAreaResponse> findMeasurePointsInRiskAreas() {
        try {
            return affectedAreaRepository.findMeasurePointsInRiskAreas();
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando la consulta: " + e.getMessage());
        }
    }
}
