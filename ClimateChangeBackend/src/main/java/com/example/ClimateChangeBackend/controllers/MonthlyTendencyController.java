package com.example.ClimateChangeBackend.controllers;

import com.example.ClimateChangeBackend.entities.MonthlyTendencyEntity;
import com.example.ClimateChangeBackend.services.MonthlyTendencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/monthly-tendency")
@CrossOrigin("*")
public class MonthlyTendencyController {

    private final MonthlyTendencyService service;

    public MonthlyTendencyController(MonthlyTendencyService monthlyTendencyService) {
        this.service = monthlyTendencyService;
    }

    @GetMapping("/get-all")
    public List<MonthlyTendencyEntity> getAll() {

        return service.getMonthlyTendencies();
    }

    @GetMapping("/sensor-type/{sensorType}")
    public List<MonthlyTendencyEntity> getBySensorType(@PathVariable String sensorType) {
        return service.getMonthlyTendenciesBySensorType(sensorType);
    }


}
