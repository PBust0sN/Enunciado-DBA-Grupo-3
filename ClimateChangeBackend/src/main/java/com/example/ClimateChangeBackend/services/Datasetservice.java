package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.DatasetRequest;
import com.example.ClimateChangeBackend.entities.DatasetEntity;
import com.example.ClimateChangeBackend.entities.UserEntity;
import com.example.ClimateChangeBackend.dtos.RegisterRequest;
import com.example.ClimateChangeBackend.repositories.DatasetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class Datasetservice {
    private DatasetRepository datasetRepository;

    public DatasetEntity getDatasetById(Long idDataset){
        return datasetRepository.findById(idDataset).get();
    }

    public DatasetEntity getDatasetByName(String nameDataset){
        return datasetRepository.findByName(nameDataset).get();
    }

    public void createDataset(DatasetRequest datasetRequest){
        DatasetEntity datasetEntity = DatasetEntity.builder()
                .nameDataset(datasetRequest.getNameDataset())
                .descriptionDataset(datasetRequest.getDescriptionDataset())
                .sourceDataset(datasetRequest.getSourceDataset())
                .dateAutorizationDataset(datasetRequest.getDateAutorization())
                .build();
        datasetRepository.save(datasetEntity);
    }
}
