package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.PointVariationDTO;
import com.example.ClimateChangeBackend.dtos.PointWithoutGeorefDTO;
import com.example.ClimateChangeBackend.entities.MeasurePointsEntity;

import java.util.List;
import java.util.Optional;

public interface MeasurePointsRepository {
    Optional<MeasurePointsEntity> findById(Long id);
    List<MeasurePointsEntity> findAll();
    MeasurePointsEntity save(MeasurePointsEntity measurePointsEntity);
    int update(MeasurePointsEntity  measurePointsEntity);
    List<PointVariationDTO> findPointsWithHighestVariation();
    List<PointWithoutGeorefDTO> findPointsWithoutGeoreference();
    Optional<MeasurePointsEntity> findByLatitudeAndLongitude(double lat, double lon);

    List<MeasurePointsEntity> getPointsLessThan50ByLatitudeAndLongitude(double latitude, double longitude);
}
