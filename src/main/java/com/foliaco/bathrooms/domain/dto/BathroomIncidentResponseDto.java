package com.foliaco.bathrooms.domain.dto;

import com.foliaco.bathrooms.domain.enums.BathroomStatus;
import com.foliaco.bathrooms.domain.enums.Gender;
import com.foliaco.bathrooms.domain.enums.IncidentMessage;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BathroomIncidentResponseDto {

    private Gender genderBathroom;
    private String nameBlock;
    private String floor;
    @Enumerated(EnumType.STRING)
    private IncidentMessage problem;
    private String comment;
    private LocalDate date;
    private String status;

}
