package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.AffectedAreaDTO;
import com.example.ClimateChangeBackend.dtos.InvalidGeometryDTO;
import com.example.ClimateChangeBackend.dtos.MeasurePointAreaResponse;
import com.example.ClimateChangeBackend.entities.AffectedAreaEntity;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.WKTReader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AffectedAreaRepositoryImpl implements AffectedAreaRepository {

    private final JdbcTemplate jdbcTemplate;
    private final GeometryFactory geometryFactory = new GeometryFactory();
    private final WKTReader wktReader = new WKTReader(geometryFactory);

    private final RowMapper<AffectedAreaEntity> mapper = (rs, rowNum) -> {
        try {
            Polygon polygon = (Polygon) wktReader.read(rs.getString("wkt"));
            return AffectedAreaEntity.builder().idArea(rs.getLong("id_area")).name(rs.getString("name")).areaType(rs.getString("area_type")).geom(polygon).build();
        } catch (Exception e) {
            throw new SQLException("Error al parsear el polígono", e);
        }
    };

    @Override
    public List<AffectedAreaEntity> findAll() {
        String sql = "SELECT id_area, name, area_type, ST_AsText(geom) AS wkt FROM affected_areas";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Optional<AffectedAreaEntity> findById(Long idArea) {
        String sql = "SELECT id_area, name, area_type, ST_AsText(geom) AS wkt FROM affected_areas WHERE id_area = ?";
        List<AffectedAreaEntity> result = jdbcTemplate.query(sql, mapper, idArea);
        return result.stream().findFirst();
    }

    @Override
    public AffectedAreaEntity save(AffectedAreaEntity area) {
        if (area.getIdArea() == null) {
            String insert = """
                    INSERT INTO affected_areas (name, area_type, geom)
                    VALUES (?, ?, ST_SetSRID(ST_GeomFromText(?, 4326), 4326))
                    RETURNING id_area
                    """;
            Long newId = jdbcTemplate.queryForObject(insert, Long.class, area.getName(), area.getAreaType(), area.getGeom().toText());
            area.setIdArea(newId);
            return area;
        } else {
            String update = """
                    UPDATE affected_areas
                       SET name = ?,
                           area_type = ?,
                           geom = ST_SetSRID(ST_GeomFromText(?, 4326), 4326)
                     WHERE id_area = ?
                    """;
            jdbcTemplate.update(update, area.getName(), area.getAreaType(), area.getGeom().toText(), area.getIdArea());
            return area;
        }
    }

    @Override
    public int deleteById(Long idArea) {
        String sql = "DELETE FROM affected_areas WHERE id_area = ?";
        return jdbcTemplate.update(sql, idArea);
    }

    // Consulta 3 enunciado 2
    @Override
    public List<MeasurePointAreaResponse> findMeasurePointsInRiskAreas() {
        String sql = """
            SELECT mp.id_measure_points,
                   mp.latitud,
                   mp.longitud,
                   aa.id_area,
                   aa.name AS area_name
              FROM measure_points mp
              JOIN affected_areas aa
                ON ST_Intersects(mp.geom, aa.geom)
             WHERE aa.area_type = 'Zonas de Riesgo Climático'
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> MeasurePointAreaResponse.builder()
                .idMeasurePoints(rs.getLong("id_measure_points"))
                .latitud(rs.getDouble("latitud"))
                .longitud(rs.getDouble("longitud"))
                .idArea(rs.getLong("id_area"))
                .areaName(rs.getString("area_name"))
                .build());
    }

    // Consulta 4.2 para puntos
    @Override
    public List<InvalidGeometryDTO> findInvalidGeometry() {
        String sql = """
                SELECT id_area AS id,
                       ST_AsText(geom) AS wkt,
                       ST_IsValidReason(geom) AS motivo
                FROM affected_areas
                WHERE NOT ST_IsValid(geom);
                """;
        try{
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(InvalidGeometryDTO.class)
            );

        }catch (EmptyResultDataAccessException e){
            return List.of();
        }
    }

    @Override
    public List<AffectedAreaDTO> findValidAffectedAreas() {
        String sql = """
                SELECT id_area as idArea,
                    name,
                    area_type as areaType,
                    ST_AsText(geom) AS wkt
                FROM affected_areas
                WHERE ST_IsValid(geom);
                """;
        try{
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(AffectedAreaDTO.class)
            );

        }catch (EmptyResultDataAccessException e){
            return List.of();
        }
    }


}
