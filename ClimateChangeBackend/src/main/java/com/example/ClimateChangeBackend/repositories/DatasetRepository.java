package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.TSMeasureDTO;
import com.example.ClimateChangeBackend.dtos.InterpolarDatosSemDTO;
import com.example.ClimateChangeBackend.entities.DatasetEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface DatasetRepository {
    List<DatasetEntity> findAll();
    Optional <DatasetEntity> findById(Long id);
    Optional <DatasetEntity> findByName(String nameDataset);
    DatasetEntity save(DatasetEntity datasetEntity);

    List<TSMeasureDTO> timeSeriesMeasure (Long id_dataset, LocalDate startDate, LocalDate endDate);

    List<InterpolarDatosSemDTO> interpolar_datos_semanales(Long id_dataset);
}
