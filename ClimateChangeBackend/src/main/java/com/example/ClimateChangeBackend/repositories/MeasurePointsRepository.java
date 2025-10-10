package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.MeasurePointsEntity;
import com.example.ClimateChangeBackend.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MeasurePointsRepository {
    Optional<MeasurePointsEntity> findById(Long id);
    List<Optional<MeasurePointsEntity>> findAll();
    MeasurePointsEntity save(MeasurePointsEntity measurePointsEntity);
    int update(MeasurePointsEntity  measurePointsEntity);
}
