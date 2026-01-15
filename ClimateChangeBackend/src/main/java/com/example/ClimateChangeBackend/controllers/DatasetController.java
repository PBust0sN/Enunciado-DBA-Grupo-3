package com.example.ClimateChangeBackend.controllers;

import com.example.ClimateChangeBackend.dtos.DatasetRequest;
import com.example.ClimateChangeBackend.dtos.InterpolarDatosSemDTO;
import com.example.ClimateChangeBackend.dtos.MessageResponse;
import com.example.ClimateChangeBackend.dtos.TSMeasureDTO;
import com.example.ClimateChangeBackend.entities.DatasetEntity;
import com.example.ClimateChangeBackend.services.DatasetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/dataset")
public class DatasetController {

    private DatasetService datasetService;

    @PostMapping("/create-dataset")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> createDataset(@Valid @RequestBody DatasetRequest datasetRequest) {
        datasetService.createDataset(datasetRequest);
        return ResponseEntity.ok().body(new MessageResponse("Dataset created successfully",true));
    }

        @GetMapping("/getById/{idDataset}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getById(@PathVariable Long idDataset) {
        DatasetEntity dataset = datasetService.getDatasetById(idDataset);
        return ResponseEntity.ok().body(dataset);
    }

    @GetMapping("/get-by-name/{nameDataset}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getByName(@PathVariable String nameDataset) {
        DatasetEntity dataset = datasetService.getDatasetByName(nameDataset);
        return ResponseEntity.ok().body(dataset);
    }

    @GetMapping("/time-serie-measure/{idDataset}/{startDate}/{endDate}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<TSMeasureDTO>> timeSeriesMeasure(
            @PathVariable Long idDataset,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<TSMeasureDTO> tsMeasureDTO = datasetService.timeSeriesMeasure(idDataset,startDate,endDate);
        return ResponseEntity.ok().body(tsMeasureDTO);
    }


    @GetMapping("/interpolar-datos-semanales/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<InterpolarDatosSemDTO>> interpolar_datos_semanales(@PathVariable("id") Long id_dataset){
        List<InterpolarDatosSemDTO> datos = datasetService.interpolar_datos_semanales(id_dataset);
        return ResponseEntity.ok().body(datos);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<List<DatasetEntity>> getAll() {
        List<DatasetEntity> datasets = datasetService.getAll();
        return ResponseEntity.ok(datasets);
    }

    @PutMapping("/{idDataset}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_EMPLOYEE')")
    public ResponseEntity<?> updateDataset(@PathVariable Long idDataset,
                                           @Valid @RequestBody DatasetRequest datasetRequest) {
        datasetService.updateDataset(idDataset, datasetRequest);
        return ResponseEntity.ok(new MessageResponse("Dataset updated successfully", true));
    }

    @DeleteMapping("/{idDataset}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteDataset(@PathVariable Long idDataset) {
        datasetService.deleteDataset(idDataset);
        return ResponseEntity.ok(new MessageResponse("Dataset deleted successfully", true));
    }
}


