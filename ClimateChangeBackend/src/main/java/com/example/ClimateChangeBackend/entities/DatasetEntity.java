package com.example.ClimateChangeBackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetEntity {
    private Long idDataset;
    private String nameDataset;
    private String descriptionDataset;
    private String sourceDataset;
    private Date dateAutorizationDataset;
}
