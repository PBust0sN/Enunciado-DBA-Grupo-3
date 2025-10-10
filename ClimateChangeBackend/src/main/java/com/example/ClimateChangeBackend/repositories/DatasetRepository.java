package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.DatasetEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface DatasetRepository {
    List<DatasetEntity> findAll();
    Optional <DatasetEntity> findById(Long id);
    Optional <DatasetEntity> findByName(String nameDataset);
    DatasetEntity save(DatasetEntity datasetEntity);

}
