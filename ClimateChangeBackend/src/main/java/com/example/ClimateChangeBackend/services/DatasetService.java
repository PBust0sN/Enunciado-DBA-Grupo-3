package com.example.ClimateChangeBackend.services;

import com.example.ClimateChangeBackend.dtos.DatasetRequest;
import com.example.ClimateChangeBackend.dtos.TSMeasureDTO;
import com.example.ClimateChangeBackend.dtos.InterpolarDatosSemDTO;
import com.example.ClimateChangeBackend.entities.DatasetEntity;
import com.example.ClimateChangeBackend.repositories.DatasetRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class DatasetService {
    private DatasetRepository datasetRepository;

    public DatasetEntity getDatasetById(Long idDataset){
        if(datasetRepository.findById(idDataset).isEmpty()){
            throw new RuntimeException("Dataset not found");
        }
        return datasetRepository.findById(idDataset).get();
    }

    public DatasetEntity getDatasetByName(String nameDataset){
        if(datasetRepository.findByName(nameDataset).isEmpty()){
            throw new RuntimeException("Dataset not found");
        }
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

    public void updateDataset(Long idDataset, @Valid DatasetRequest datasetRequest) {
        DatasetEntity datasetEntity = getDatasetById(idDataset);
        datasetEntity.setNameDataset(datasetRequest.getNameDataset());
        datasetEntity.setDescriptionDataset(datasetRequest.getDescriptionDataset());
        datasetEntity.setSourceDataset(datasetRequest.getSourceDataset());
        datasetEntity.setDateAutorizationDataset(datasetRequest.getDateAutorization());
        datasetRepository.save(datasetEntity);
    }

    public void deleteDataset(Long idDataset) {
        datasetRepository.deleteById(idDataset);
    }
}
