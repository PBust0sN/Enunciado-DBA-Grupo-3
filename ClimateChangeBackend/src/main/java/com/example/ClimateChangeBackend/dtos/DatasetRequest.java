package com.example.ClimateChangeBackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DatasetRequest {

    @NotBlank(message = "Name is required")
    @JsonProperty("name_dataset")
    private String nameDataset;

    @NotBlank(message = "Description is required")
    @JsonProperty("description_dataset")
    private String descriptionDataset;

    @NotBlank(message = "Source is required")
    @JsonProperty("source_dataset")
    private String sourceDataset;

    @NotBlank(message = "Date autorization is required")
    @JsonProperty("data_autorization_dataset")
    private Date dateAutorization;

}
