package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.MeasurePointsEntity;
import com.example.ClimateChangeBackend.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class MeasurePointsRepositoryImp implements MeasurePointsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MeasurePointsRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<MeasurePointsEntity> findById(Long id){
        String sql = "SELECT idMeasurePoints, Latitud, Longitud, SensorType FROM measure_points WHERE idMeasurePoints = ?";
        try {
            MeasurePointsEntity measurePointsEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(MeasurePointsEntity.class),
                    id
            );
            return Optional.ofNullable(measurePointsEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<MeasurePointsEntity> findAll(){
        String sql = "SELECT * FROM measure_points";
        try {
            List<MeasurePointsEntity> measurePointsEntity = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(MeasurePointsEntity.class)
            );
            return measurePointsEntity;
        } catch (EmptyResultDataAccessException e) {
            return List.of(null);
        }
    }

    @Override
    public MeasurePointsEntity  save(MeasurePointsEntity measurePointsEntity){
        if (measurePointsEntity.getIdMeasurePoints() == null) {
            String sql = "INSERT INTO measure_points (latitud, longitud, sensorType) VALUES (?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(
                                sql,
                                new String[] { "idMeasurePoints" }
                        );
                        ps.setLong(1, measurePointsEntity.getLatitud());
                        ps.setLong(2, measurePointsEntity.getLongitud());
                        ps.setString(3, measurePointsEntity.getSensorType());
                        return ps;
                    },
                    keyHolder
            );
            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                measurePointsEntity.setIdMeasurePoints(generatedId.longValue());
            }
        } else {
            String sql = "UPDATE measure_points SET latitud = ?, longitud = ?, sensorType = ? WHERE idMeasurePoints = ?";
            jdbcTemplate.update(
                sql,
                measurePointsEntity.getIdMeasurePoints(),
                measurePointsEntity.getLatitud(),
                measurePointsEntity.getLongitud(),
                measurePointsEntity.getSensorType()
            );
        }
        return measurePointsEntity;
    }

    @Override
    public int update(MeasurePointsEntity  measurePointsEntity){
        String sql = "UPDATE measure_points SET longitud = ?, latitud = ?, sensorType = ? WHERE idMeasurePoints = ?";
        return jdbcTemplate.update(sql, measurePointsEntity.getLongitud(),
                measurePointsEntity.getLatitud(),
                measurePointsEntity.getSensorType(),
                measurePointsEntity.getIdMeasurePoints());
    }
}
