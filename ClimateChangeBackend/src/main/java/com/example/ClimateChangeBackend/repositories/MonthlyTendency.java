package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.MonthlyTendencyEntity;
import java.util.List;
public interface MonthlyTendency {
    List<MonthlyTendencyEntity> findAll();
    List<MonthlyTendencyEntity> findBySensorType(String sensorType);
    MonthlyTendencyEntity findById(Integer id);

}
