package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.MeasurePointRequest;
import com.example.ClimateChangeBackend.entities.MeasurePointsEntity;
import com.example.ClimateChangeBackend.repositories.MeasurePointsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MeasurePointsService {

    private MeasurePointsRepository measurePointsRepository;

    public Optional<MeasurePointsEntity> findById(Long id) {
        return  measurePointsRepository.findById(id);
    }

    public List<Optional<MeasurePointsEntity>> findAll(){
        return   measurePointsRepository.findAll();
    }

    public MeasurePointsEntity save(MeasurePointRequest measurePointRequest) {
        MeasurePointsEntity measurePointsEntity =MeasurePointsEntity.builder()
                .latitud(measurePointRequest.getLatitud())
                .longitud(measurePointRequest.getLongitud())
                .sensorType(measurePointRequest.getSensorType())
                .build();
        return  measurePointsRepository.save(measurePointsEntity);
    }

    public int update(MeasurePointRequest measurePointRequest) {
        MeasurePointsEntity measurePointsEntity =MeasurePointsEntity.builder()
                .latitud(measurePointRequest.getLatitud())
                .longitud(measurePointRequest.getLongitud())
                .sensorType(measurePointRequest.getSensorType())
                .build();
        return measurePointsRepository.update(measurePointsEntity);
    }
}
