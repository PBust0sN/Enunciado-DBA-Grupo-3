package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.AnomaliaDTO;
import com.example.ClimateChangeBackend.entities.MeasurementEntity;
import com.example.ClimateChangeBackend.repositories.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MeasurementService {
    private MeasurementRepository measurementRepository;

    public boolean checkMeasurementExists(long id){
        return measurementRepository.findById(id).isPresent();
    }

    public void deleteMeasurementById(long id){
        measurementRepository.deleteById(id);
    }

    public Optional<MeasurementEntity> updateMeasurement(MeasurementEntity measurementEntity){
        if(checkMeasurementExists(measurementEntity.getId_measurement())){
            return Optional.of(measurementRepository.save(measurementEntity));
        }
        return Optional.empty();
    }

    public Optional<MeasurementEntity> getMeasurementById(long id){
        return measurementRepository.findById(id);
    }

    public List<Map<String, Object>> extremeEventDetection() {
        return measurementRepository.extremeEventDetection();
    public List<AnomaliaDTO> tempetureAnomalyCalculation(){
        return measurementRepository.tempetureAnomalyCalculation();
    }
}
