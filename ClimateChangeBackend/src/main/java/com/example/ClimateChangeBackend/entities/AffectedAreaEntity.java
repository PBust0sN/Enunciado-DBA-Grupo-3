package com.example.ClimateChangeBackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "affected_areas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffectedAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Long idArea;

    @Column(nullable = false)
    private String name;

    @Column(name = "area_type", nullable = false)
    private String areaType;

    @Column(name = "geom", nullable = false, columnDefinition = "geometry(Polygon,4326)")
    private Polygon geom;
}