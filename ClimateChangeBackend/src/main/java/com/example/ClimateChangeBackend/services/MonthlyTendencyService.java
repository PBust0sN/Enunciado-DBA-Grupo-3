package com.example.ClimateChangeBackend.services;
import com.example.ClimateChangeBackend.entities.MonthlyTendencyEntity;
import com.example.ClimateChangeBackend.repositories.MonthlyTendencyImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MonthlyTendencyService {


    private JdbcTemplate jdbcTemplate;
    private MonthlyTendencyImpl monthlyTendencyRepository;
    private static final Logger log =
            LoggerFactory.getLogger(MonthlyTendencyService.class);


    public List<MonthlyTendencyEntity> getMonthlyTendencies() {
        return monthlyTendencyRepository.findAll();
    }

    public List<MonthlyTendencyEntity> getMonthlyTendenciesBySensorType(String sensorType) {
        return monthlyTendencyRepository.findBySensorType(sensorType);
    }

    public void refreshMonthlyTendency() {
        try {
            jdbcTemplate.execute(
                    "REFRESH MATERIALIZED VIEW CONCURRENTLY tendencia_mensual;"
            );
        } catch (DataAccessException e) {
            log.warn("No se pudo refrescar la vista materializada tendencia_mensual", e);
        }
    }




}
