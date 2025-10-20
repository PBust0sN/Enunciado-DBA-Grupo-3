package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.DatasetEntity;
import com.example.ClimateChangeBackend.entities.MonthlyTendencyEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MonthlyTendencyImpl implements MonthlyTendency {

    private final JdbcTemplate jdbcTemplate;
    public MonthlyTendencyImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MonthlyTendencyEntity> findAll() {
        String sql = "SELECT * FROM tendencia_mensual";
        try{
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MonthlyTendencyEntity.class));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e){
            throw new RuntimeException("Error al obtener tendencia mensual",e);
        }
    }

    @Override
    public List<MonthlyTendencyEntity> findBySensorType(String sensorType) {
        String sql = "SELECT * FROM tendencia_mensual WHERE tipo_sensor = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MonthlyTendencyEntity.class), sensorType);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tendencia mensual por tipo de sensor", e);
        }
    }
    @Override
    public MonthlyTendencyEntity findById(Integer id) {
        String sql = "SELECT * FROM tendencia_mensual WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(MonthlyTendencyEntity.class),
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tendencia mensual por ID", e);
        }
    }

}
