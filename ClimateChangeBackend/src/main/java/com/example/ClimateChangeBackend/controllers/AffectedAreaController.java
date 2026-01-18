package com.example.ClimateChangeBackend.controllers;

import com.example.ClimateChangeBackend.dtos.*;
import com.example.ClimateChangeBackend.entities.AffectedAreaEntity;
import com.example.ClimateChangeBackend.services.AffectedAreaService;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/api/v1/affected-areas")
public class AffectedAreaController {

    private final AffectedAreaService affectedAreaService;
    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final WKTReader wktReader = new WKTReader(geometryFactory);

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getAllAffectedAreas() {
        List<AffectedAreaResponse> result = affectedAreaService.findAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{idArea}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getAffectedAreaById(@PathVariable Long idArea) {
        AffectedAreaEntity area = affectedAreaService.findById(idArea);
        if (area == null) {
            return ResponseEntity.status(404).body("Affected area not found");
        }
        return ResponseEntity.ok(toResponse(area));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> createAffectedArea(@RequestBody AffectedAreaRequest request) {
        try {
            Polygon polygon = (Polygon) wktReader.read(request.getWkt());
            AffectedAreaEntity saved = affectedAreaService.save(
                    AffectedAreaEntity.builder()
                            .name(request.getName())
                            .areaType(request.getAreaType())
                            .geom(polygon)
                            .build()
            );
            return ResponseEntity.ok(toResponse(saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("WKT inválido: " + e.getMessage());
        }
    }

    @DeleteMapping("/{idArea}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> deleteAffectedArea(@PathVariable Long idArea) {
        int rowsAffected = affectedAreaService.deleteById(idArea);
        if (rowsAffected == 0) {
            return ResponseEntity.status(404).body("Affected area not found");
        }
        return ResponseEntity.ok("Affected area deleted successfully");
    }


    // Consulta 3 enunciado 2
    @GetMapping("/risk-measure-points")
    public ResponseEntity<List<MeasurePointAreaResponse>> getMeasurePointsInRiskAreas() {
        return ResponseEntity.ok(affectedAreaService.findMeasurePointsInRiskAreas());
    }

    // Consulta 4 lab2
    @GetMapping("/getInvalidGeometry")
    public ResponseEntity<List<InvalidGeometryDTO>> getInvalidGeometry() {
        return ResponseEntity.ok().body(affectedAreaService.getInvalidGeometry());
    }

    @GetMapping("/getValidAreas")
    public ResponseEntity<List<AffectedAreaDTO>> getValidAreas() {
        return ResponseEntity.ok().body(affectedAreaService.getValidAreas());
    }



    private AffectedAreaResponse toResponse(AffectedAreaEntity entity) {
        return AffectedAreaResponse.builder()
                .idArea(entity.getIdArea())
                .name(entity.getName())
                .areaType(entity.getAreaType())
                .wkt(entity.getGeom().toText())
                .build();
    }
}
