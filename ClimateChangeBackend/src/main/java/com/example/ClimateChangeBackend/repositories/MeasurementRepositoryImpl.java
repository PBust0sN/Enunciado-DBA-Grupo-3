package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.AnomaliaDTO;
import com.example.ClimateChangeBackend.entities.DatasetEntity;
import com.example.ClimateChangeBackend.entities.MeasurementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
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
        String sql = "SELECT id_measurement, value_measurement, date_measurement, id_measure_points, id_dataset FROM measurements WHERE id_measurement = ?";
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
    public MeasurementEntity update(MeasurementEntity measurementEntity) {
        String sql = "INSERT INTO measurements (value_measurement, date_measurement, id_measure_points, id_dataset) VALUES (?, ?, ?, ?)";
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

    // Consulta 4
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

    // Consulta 1
    @Override
    public List<AnomaliaDTO> tempetureAnomalyCalculation(){
        String sql = """
                     SELECT
                     id_measure_points,
                     AVG(CASE WHEN date_measurement >= CURRENT_DATE - INTERVAL '1 year' THEN value_measurement END) - AVG(value_measurement) AS anomalia FROM measurements
                     GROUP BY id_measure_points""";
        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(AnomaliaDTO.class)
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public MeasurementEntity create(MeasurementEntity measurementEntity){
        String sql = "INSERT INTO measurements (value_measurement, date_measurement, id_measure_points, id_dataset) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection ->  {
                    PreparedStatement ps = connection.prepareStatement(
                            sql,
                            new String[] { "id_measurement" }
                    );
                    ps.setDouble(1, measurementEntity.getValue_measurement());
                    ps.setObject(2, measurementEntity.getDate_measurement(), Types.TIMESTAMP_WITH_TIMEZONE);
                    ps.setInt(3, measurementEntity.getId_points_measurement());
                    ps.setInt(4, measurementEntity.getId_dataset());
                    return ps;
                },
                keyHolder
        );
        Number generatedId = keyHolder.getKey();
        if(generatedId != null) {
            measurementEntity.setId_measurement(generatedId.longValue());
        }

        return measurementEntity;
    }

    @Override
    public List<MeasurementEntity> findAll(){
        String sql = "SELECT * FROM measurements";
        try{
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MeasurementEntity.class));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e){
            throw new RuntimeException("Error al obtener las mediciones",e);
        }
    }
}
