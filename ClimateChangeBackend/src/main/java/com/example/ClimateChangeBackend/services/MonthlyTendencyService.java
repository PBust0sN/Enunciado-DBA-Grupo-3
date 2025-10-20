package com.example.ClimateChangeBackend.services;
import com.example.ClimateChangeBackend.entities.MonthlyTendencyEntity;
import com.example.ClimateChangeBackend.repositories.MonthlyTendencyImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MonthlyTendencyService {


    private JdbcTemplate jdbcTemplate;
    private MonthlyTendencyImpl monthlyTendencyRepository;

    public List<MonthlyTendencyEntity> getMonthlyTendencies() {
        return monthlyTendencyRepository.findAll();
    }

    public List<MonthlyTendencyEntity> getMonthlyTendenciesBySensorType(String sensorType) {
        return monthlyTendencyRepository.findBySensorType(sensorType);
    }

    public void refreshMonthlyTendency() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW CONCURRENTLY tendencia_mensual;");
    }


}
