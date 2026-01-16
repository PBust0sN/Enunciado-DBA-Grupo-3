package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.*;
import com.example.ClimateChangeBackend.entities.MeasurePointsEntity;
import com.example.ClimateChangeBackend.repositories.MeasurePointsRepository;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MeasurePointsService {

    private MeasurePointsRepository measurePointsRepository;
    private GeometryFactory geometryFactory;

    public Optional<MeasurePointsEntity> findById(Long id) {
        return measurePointsRepository.findById(id);
    }

    public List<MeasurePointsEntity> findAll() {
        return measurePointsRepository.findAll();
    }

    public MeasurePointsEntity save(MeasurePointRequest measurePointRequest) {
        MeasurePointsEntity measurePointsEntity = MeasurePointsEntity.builder()
                .latitud(measurePointRequest.getLatitud())
                .longitud(measurePointRequest.getLongitud())
                .geom(geometryFactory.createPoint(
                        new Coordinate(measurePointRequest.getLongitud(), measurePointRequest.getLatitud())))
                .sensorType(measurePointRequest.getSensorType())
                .build();
        return measurePointsRepository.save(measurePointsEntity);
    }

    public int update(MeasurePointRequest measurePointRequest) {
        MeasurePointsEntity measurePointsEntity = MeasurePointsEntity.builder()
                .latitud(measurePointRequest.getLatitud())
                .longitud(measurePointRequest.getLongitud())
                .sensorType(measurePointRequest.getSensorType())
                .build();
        return measurePointsRepository.update(measurePointsEntity);
    }

    // Consulta 2
    public List<PointVariationDTO> findPointsWithHighestVariation() {
        try {
            List<PointVariationDTO> pointsWithHighestVariation = measurePointsRepository.findPointsWithHighestVariation();
            if (pointsWithHighestVariation.isEmpty()) {
                throw new RuntimeException("No points with variation data found");
            }
            return pointsWithHighestVariation;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving points with highest variation", e);
        }
    }

    public List<PointWithoutGeorefDTO> findPointsWithoutGeoreference() {
        try {
            List<PointWithoutGeorefDTO> pointsWithoutGeoreference = measurePointsRepository.findPointsWithoutGeoreference();
            if (pointsWithoutGeoreference == null || pointsWithoutGeoreference.isEmpty()) {
                throw new RuntimeException("No points without georeference data found");
            }
            return pointsWithoutGeoreference;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving points without georeference", e);
        }
    }

    public Optional<MeasurePointsEntity> getMeasurePointByLatitudAndLongitud(double latitud, double longitud, String type) {
        return measurePointsRepository.findByLatitudeAndLongitude(latitud, longitud, type);
    }

    // Consulta 3
    public List<MeasurePointsEntity> getPointsLessThan50(double lat, double lon) {
        MeasurePointsEntity point = measurePointsRepository.findByLatitudeAndLongitude(lat, lon, "Temperatura")
                .orElseThrow(() -> new RuntimeException("No se encontró el punto con esas coordenadas"));
        if (!point.getSensorType().equals("Temperatura")) {
            throw new RuntimeException("Este no es un punto de Temperatura");
        }
        return measurePointsRepository.getPointsLessThan50ByLatitudeAndLongitude(lat, lon);
    }

    // Consulta 4.2 para puntos
    public List<InvalidPointDTO> getInvalidPoints() {
        return measurePointsRepository.findInvalidPoints();
    }

    public List<CO2DistanceDTO> get50kmCO2Temperature(){
        return measurePointsRepository.get50kmCO2Temperature();
    }
}
