package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.DatasetRequest;
import com.example.ClimateChangeBackend.dtos.TSMeasureDTO;
import com.example.ClimateChangeBackend.dtos.InterpolarDatosSemDTO;
import com.example.ClimateChangeBackend.entities.DatasetEntity;
import com.example.ClimateChangeBackend.entities.UserEntity;
import com.example.ClimateChangeBackend.dtos.RegisterRequest;
import com.example.ClimateChangeBackend.repositories.DatasetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<DatasetEntity> getAll(){
        return datasetRepository.findAll();
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

    public List<TSMeasureDTO> timeSeriesMeasure(Long id_dataset, LocalDate startDate, LocalDate endDate) {
        return datasetRepository.timeSeriesMeasure(id_dataset, startDate, endDate);
    }

    public List<InterpolarDatosSemDTO> interpolar_datos_semanales(Long id_dataset){
        return  datasetRepository.interpolar_datos_semanales(id_dataset);
    }
}
