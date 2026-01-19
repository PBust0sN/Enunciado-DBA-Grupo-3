package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.*;
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
    Optional<MeasurePointsEntity> findByLatitudeAndLongitude(double lat, double lon, String type);
    List<InvalidPointDTO> findInvalidPoints();
    List<MeasurePointDTO> getNoSensor();
    List<MeasurePointsEntity> getPointsLessThan50ByLatitudeAndLongitude(double latitude, double longitude);

    // vista monthly tendency
    List<MonthlyTendencyDTO> findAllMonthlyTendencies();
    List<MonthlyTendencyDTO> findBySensorType(String sensorType);
    Double interpolateByNearestNeighbors(double lat, double lon);
    List<CO2DistanceDTO> get50kmCO2Temperature();
}
