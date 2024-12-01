package com.foliaco.bathrooms.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BathroomIncidentDto {

    private Integer idBathroom;
    private Integer idIncident;
    private LocalDate date;
    private String status;

}
