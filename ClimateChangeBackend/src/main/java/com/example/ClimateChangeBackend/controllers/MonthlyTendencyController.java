package com.example.ClimateChangeBackend.controllers;

import com.example.ClimateChangeBackend.entities.MonthlyTendencyEntity;
import com.example.ClimateChangeBackend.services.MonthlyTendencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monthly-tendency")
public class MonthlyTendencyController {

    private final MonthlyTendencyService service;

    public MonthlyTendencyController(MonthlyTendencyService monthlyTendencyService) {
        this.service = monthlyTendencyService;
    }

    @GetMapping
    public List<MonthlyTendencyEntity> getAll() {
        return service.getMonthlyTendencies();
    }

    @GetMapping("/sensor-type/{sensorType}")
    public List<MonthlyTendencyEntity> getBySensorType(@PathVariable String sensorType) {
        return service.getMonthlyTendenciesBySensorType(sensorType);
    }


}
