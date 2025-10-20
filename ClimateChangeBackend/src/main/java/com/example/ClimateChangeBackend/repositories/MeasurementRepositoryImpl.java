package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.AnomaliaDTO;
import com.example.ClimateChangeBackend.entities.MeasurementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MeasurementRepositoryImpl implements MeasurementRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MeasurementRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<MeasurementEntity> findById(long id) {
        String sql = "SELECT id_measurement, value_measurement, date_measurement, id_points_measurement, id_dataset FROM measurements WHERE id_measurement = ?";
        try {
            MeasurementEntity measurementEntity = jdbcTemplate.queryForObject(
                    sql,
                    new org.springframework.jdbc.core.BeanPropertyRowMapper<>(MeasurementEntity.class),
                    id
            );
            return Optional.ofNullable(measurementEntity);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public MeasurementEntity save(MeasurementEntity measurementEntity) {
        String sql = "INSERT INTO measurements (value_measurement, date_measurement, id_points_measurement, id_dataset) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                measurementEntity.getValue_measurement(),
                measurementEntity.getDate_measurement(),
                measurementEntity.getId_points_measurement(),
                measurementEntity.getId_dataset()
        );
        return measurementEntity;
    }

    @Override
    public int deleteById(long id) {
        String sql = "DELETE FROM measurements WHERE id_measurement = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Map<String, Object>> extremeEventDetection() {
        String sql = """
            SELECT
                DATE(m.date_measurement) AS fecha,
                MAX(m.value_measurement) AS temperatura_maxima
            FROM
                measurements m
            JOIN
                measure_points p ON m.id_measure_points = p.id_measure_points
            WHERE
                p.sensor_type = 'Temperatura'
                AND m.date_measurement >= NOW() - INTERVAL '1 year'
            GROUP BY
                DATE(m.date_measurement)
            HAVING
                MAX(m.value_measurement) > 35
            ORDER BY
                fecha;
            """;

        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<AnomaliaDTO> tempetureAnomalyCalculation(){
        String sql = """
                     SELECT
                     id_measure_points,
                     AVG(CASE WHEN date_measurement >= CURRENT_DATE - INTERVAL '1 year' THEN value_measurement END) - AVG(value_measurement) AS anomalia 
                     FROM measurements 
                     GROUP BY id_measure_points""";
        try {
            List<AnomaliaDTO> anomaliaDTO = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(AnomaliaDTO.class)
            );
            return anomaliaDTO;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
